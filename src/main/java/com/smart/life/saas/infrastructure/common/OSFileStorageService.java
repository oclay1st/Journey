package com.smart.life.saas.infrastructure.common;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.common.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class OSFileStorageService implements FileStorageService {

    @Value("${journey.upload.path}")
    private String uploadBasePath;

    @Override
    public Path resolveFilePath(String parentPath, String fileName) {
        int lastPosition = fileName.lastIndexOf(".");
        String extension = fileName.substring(lastPosition);
        return Paths.get(uploadBasePath, parentPath, UUID.randomUUID().toString() + extension);
    }

    @Override
    public Path saveFile(MultipartFile multipartFile, Path path) {
        Path filePath = path.normalize();
        if (!path.isAbsolute()) {
            throw JourneyException.preconditionFailed("The file path must be absolute");
        }
        try (InputStream fileStream = multipartFile.getInputStream()) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectory(filePath.getParent());
            }
            Files.copy(fileStream, filePath);
        } catch (IOException ex) {
            throw JourneyException.unexpected("Error handling files", ex);
        }
        return filePath;
    }

    @Override
    public void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException ex) {
            throw JourneyException.unexpected("Error deleting file with path :" + path.toString(), ex);
        }
    }
}
