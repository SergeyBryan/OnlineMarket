package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    String saveImage(MultipartFile image);

    void updateImage(String pathToOldImage, MultipartFile image) throws IOException;
}
