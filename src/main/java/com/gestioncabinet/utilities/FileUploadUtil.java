package com.gestioncabinet.utilities;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    public static void deleteFile(String uploadDir,String fileName) throws IOException {
        Path path=Paths.get(uploadDir);
        Path filePath=path.resolve(fileName);
        Files.deleteIfExists(filePath);
    }
}