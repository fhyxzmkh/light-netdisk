package com.backend.controller.file;

import com.backend.controller.common.ABaseController;
import com.backend.entity.dto.UploadResultDto;
import com.backend.entity.vo.ResponseVO;
import com.backend.service.FileService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController extends ABaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file/upload")
    public ResponseVO uploadFile(
            String fileId,
            MultipartFile file,
            @NotNull String fileName,
            @NotNull String filePid,
            @NotNull String fileMd5,
            @NotNull Integer chunkIndex,
            @NotNull Integer totalChunk
    ) {

        UploadResultDto result = fileService.uploadFile(fileId, file, fileName, filePid, fileMd5, chunkIndex, totalChunk);
        return getSuccessResponseVO(result);

    }

}
