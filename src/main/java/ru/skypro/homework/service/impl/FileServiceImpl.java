package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.FileService;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${path.to.image}")
    private String imagesPath;

    @Value("${path.to.image.users}")
    private String usersImagesPath;

    @Value("${path.to.image.ads}")
    private String adsImagesPath;

    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final Marker ERROR_MARKER = MarkerFactory.getMarker("FILES_ERROR");

    @PostConstruct
    private void init() {
        logger.info("File service init method");
        Path path = Path.of(imagesPath);
        Path path1 = Path.of(imagesPath + usersImagesPath);
        Path path2 = Path.of(imagesPath + adsImagesPath);
        Path path3 = Path.of(imagesPath + "/images");
        try {
            if (Files.notExists(path)) {
                Files.createDirectory(path.toAbsolutePath());
                logger.debug("Create path {}", path.toAbsolutePath());
            }
            if (Files.notExists(path3)) {
                Files.createDirectory(path3.toAbsolutePath());
                logger.debug("Create path {}", path3.toAbsolutePath());
            }
            if (Files.notExists(path1)) {
                Files.createDirectory(path1.toAbsolutePath());
                logger.debug("Create path {}", path1.toAbsolutePath());
            }
            if (Files.notExists(path2)) {
                Files.createDirectory(path2.toAbsolutePath());
                logger.debug("Create path {}", path2.toAbsolutePath());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String saveImage(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        String pathToFile = imagesPath + usersImagesPath + fileName;
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
    public String saveUserImage(MultipartFile file) {
        String filePathInStorage = usersImagesPath + File.separator + getNewFileName(file);
        File newFile = new File(imagesPath + filePathInStorage);
        uploadFile(file, newFile);
        logger.debug("User image saved {}", newFile);
        return filePathInStorage;
    }

    @Override
    public String saveAdsImage(MultipartFile file) {
        String filePathInStorage = imagesPath + adsImagesPath + File.separator + getNewFileName(file);
        File newFile = new File(filePathInStorage);
        uploadFile(file, newFile);
        log.debug("Ad image saved {}", newFile);
        return filePathInStorage;
    }

    private String getNewFileName(MultipartFile file) {
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension = split[split.length - 1];
        String newFileName = split[0] + "." + extension;
        log.debug("Get new file name {}", newFileName);
        return newFileName;
    }

    private void uploadFile(MultipartFile file, File newFIle) {
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             FileOutputStream fos = new FileOutputStream(newFIle);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            while (bis.read(buffer) > 0) {
                bos.write(buffer);
            }
        } catch (IOException e) {
            log.info("Error uploading file {} or creating new file",
                    newFIle.getAbsoluteFile());
            throw new RuntimeException();
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
