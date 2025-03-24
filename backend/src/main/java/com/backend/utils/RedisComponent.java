package com.backend.utils;

import com.backend.entity.dto.UserSpaceDto;
import com.backend.entity.po.FileInfo;
import com.backend.entity.query.FileInfoQuery;
import com.backend.mappers.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.backend.entity.constant.Constants.*;

@Component
public class RedisComponent  {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;

    public void saveUserSpaceDto(Integer userId, UserSpaceDto userSpaceDto) {
        redisUtil.setex(
                REDIS_KEY_USER_SPACE_USE + userId,
                userSpaceDto,
                REDIS_KEY_EXPIRES_DAY
        );
    }

    public UserSpaceDto getUserSpaceUse(Integer userId) {
        UserSpaceDto spaceDto = (UserSpaceDto) redisUtil.get(REDIS_KEY_USER_SPACE_USE + userId);

        if (spaceDto == null) {
            spaceDto = new UserSpaceDto();
            Long useSpace = fileInfoMapper.selectUseSpace(userId);
            spaceDto.setUseSpace(useSpace);
            spaceDto.setTotalSpace(USER_INIT_TOTAL_SPACE);
            saveUserSpaceDto(userId, spaceDto);
        }

        return spaceDto;
    }

    // 保存
    public void saveFileTempSize(Integer userId, String fileId, Long fileSize) {
        Long currentSize = getFileTempSize(userId, fileId);
        redisUtil.setex(
                REDIS_KEY_USER_FILE_TEMP + userId + fileId,
                currentSize + fileSize,
                REDIS_KEY_EXPIRES_ONE_HOUR
        );
    }

    // 获取临时文件大小
    public Long getFileTempSize(Integer userId, String fileId) {
        Long currentSize = getFileSizeFromRedis(REDIS_KEY_USER_FILE_TEMP + userId + fileId);
        return currentSize;
    }

    private Long getFileSizeFromRedis(String key) {
        Object sizeObj = redisUtil.get(key);

        if (sizeObj == null) {
            return 0L;
        }

        if (sizeObj instanceof Integer) {
            return ((Integer) sizeObj).longValue();
        }
        else if (sizeObj instanceof Long) {
            return (Long) sizeObj;
        }

        return 0L;
    }

}
