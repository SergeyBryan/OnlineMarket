package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.service.FileService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${path.to.file.folder}")
    private String filePath;

    private static final Marker ERROR_MARKER = MarkerFactory.getMarker("FILES_ERROR");

    @Override
    public String saveImage(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        String pathToFile = filePath + fileName;
        try (FileOutputStream fos = new FileOutputStream(pathToFile)) {
            fos.write(image.getBytes());
            return pathToFile;

        } catch (IOException e) {
            log.error(
                    ERROR_MARKER,
                    "Ошибка сохранения изображения объявления, метод saveImage "
                            + e.getMessage()
                            + Arrays.toString(e.getStackTrace()),
                    e
            );
            return "";
        }
    }

    @Override
    public void updateImage(String pathToOldImage, MultipartFile image) {
        try (FileOutputStream fos = new FileOutputStream(pathToOldImage)) {
            fos.write(image.getBytes());

        } catch (IOException e) {
            log.error(
                    ERROR_MARKER,
                    "Ошибка сохранения изображения объявления, метод updateImage"
                            + e.getMessage()
                            + Arrays.toString(e.getStackTrace()),
                    e
            );
        }
    }
}
