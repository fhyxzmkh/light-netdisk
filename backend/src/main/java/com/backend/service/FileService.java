package com.backend.service;

import com.backend.entity.dto.UploadResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    UploadResultDto uploadFile(String fileId,
                               MultipartFile file,
                               String fileName,
                               String filePid,
                               String fileMd5,
                               Integer chunkIndex,
                               Integer totalChunk);

}
