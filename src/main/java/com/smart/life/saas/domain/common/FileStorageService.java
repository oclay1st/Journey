package com.smart.life.saas.domain.common;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {

    Path resolveFilePath(String parentName, String fileName);

    Path saveFile(MultipartFile file, Path path);

    void deleteFile(Path path);
}
