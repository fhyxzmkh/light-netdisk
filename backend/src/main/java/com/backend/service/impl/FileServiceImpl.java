package com.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.backend.config.AppConfig;
import com.backend.entity.dto.UploadResultDto;
import com.backend.entity.dto.UserSpaceDto;
import com.backend.entity.enums.FileDelFlagEnums;
import com.backend.entity.enums.FileStatusEnums;
import com.backend.entity.enums.ResponseCodeEnum;
import com.backend.entity.enums.UploadStatusEnums;
import com.backend.entity.po.FileInfo;
import com.backend.entity.po.User;
import com.backend.entity.query.FileInfoQuery;
import com.backend.entity.query.SimplePage;
import com.backend.entity.query.UserInfoQuery;
import com.backend.exception.BusinessException;
import com.backend.mappers.FileInfoMapper;
import com.backend.mappers.UserInfoMapper;
import com.backend.service.FileService;
import com.backend.utils.RedisComponent;
import com.backend.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.backend.utils.StringTools.renameFileWithUUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;

    @Autowired
    private UserInfoMapper<User, UserInfoQuery> userInfoMapper;

    @Autowired
    private AppConfig appConfig;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadResultDto uploadFile(
            String fileId,
            MultipartFile file,
            String fileName,
            String filePid,
            String fileMd5,
            Integer chunkIndex,
            Integer totalChunk) {
        UploadResultDto resultDto = new UploadResultDto();

        try {

            if (StrUtil.isBlank(fileId)) {
                fileId = StrUtil.uuid().replace("-", "");
            }
            resultDto.setFileId(fileId);
            Date curDate = new Date();

            Integer userId = UserUtil.getLoggedUser().getUserId();

            UserSpaceDto userSpaceDto = redisComponent.getUserSpaceUse(userId);

            if (chunkIndex == 0) {
                FileInfoQuery query = new FileInfoQuery();
                query.setFileMd5(fileMd5);
                query.setSimplePage(new SimplePage(0, 1));
                query.setStatus(FileStatusEnums.TRANSFER_SUCCESS.getStatus());
                List<FileInfo> dbFileList = fileInfoMapper.selectList(query);

                /**
                 * 秒传
                 * 通过md5值在数据库中找到文件，说明文件已存在，则秒传，不再次传输
                 */
                if (!dbFileList.isEmpty()) {
                    FileInfo dbFile = dbFileList.get(0);
                    // 判断文件大小
                    if (dbFile.getFileSize() + userSpaceDto.getUseSpace() > userSpaceDto.getTotalSpace()) {
                        throw new BusinessException(ResponseCodeEnum.CODE_904);
                    }

                    dbFile.setFileId(fileId);
                    dbFile.setFilePid(filePid);
                    dbFile.setUserId(userId);
                    dbFile.setCreateTime(curDate);
                    dbFile.setLastUpdateTime(curDate);
                    dbFile.setStatus(FileStatusEnums.TRANSFER_SUCCESS.getStatus());
                    dbFile.setDelFlag(FileDelFlagEnums.USING.getFlag());
                    dbFile.setFileMd5(fileMd5);
                    dbFile.setFileName(renameFileWithUUID(fileName));

                    fileInfoMapper.insert(dbFile);

                    resultDto.setStatus(UploadStatusEnums.SECONDS_UPLOAD.getCode());
                    // 更新用户使用空间
                    updateUserSpace(userId, dbFile.getFileSize());
                    return resultDto;
                }
            }

            /**
             * 正常分片上传
             */
            // 判断磁盘空间
            Long currentTempSize = redisComponent.getFileTempSize(userId, fileId);
            if (file.getSize() + currentTempSize + userSpaceDto.getUseSpace() > userSpaceDto.getTotalSpace()) {
                throw new BusinessException(ResponseCodeEnum.CODE_904);
            }

            // 暂存临时目录
            String tempFolderPath = appConfig.getTempFolderPath();
            String currentUserFolderName = userId + fileId;

            File tempFileFolder = new File(tempFolderPath + currentUserFolderName);
            if (!tempFileFolder.exists()) {
                tempFileFolder.mkdirs();
            }

            File newFile = new File(tempFileFolder.getPath() + "/" + chunkIndex);
            file.transferTo(newFile);

            if (chunkIndex < totalChunk - 1) {
                resultDto.setStatus(UploadStatusEnums.UPLOADING.getCode());
                redisComponent.saveFileTempSize(userId, fileId, file.getSize());
                return resultDto;
            }

        } catch (IOException e) {
            logger.error("文件上传失败", e);
        }

        return resultDto;
    }

    private void updateUserSpace(Integer userId, Long useSpace) {
        Integer count = userInfoMapper.updateUserSpace(userId, useSpace, null);
        if (count == 0) {
            throw new BusinessException(ResponseCodeEnum.CODE_904);
        }

        UserSpaceDto spaceDto = redisComponent.getUserSpaceUse(userId);
        spaceDto.setUseSpace(useSpace + spaceDto.getUseSpace());
        redisComponent.saveUserSpaceDto(userId, spaceDto);

    }

}

