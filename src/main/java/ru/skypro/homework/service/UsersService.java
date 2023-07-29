package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.Users;

public interface UsersService {

    Users save(Users user);

    Users getByID(int ID);

    String[] editUser(String ... newValue);

    void editImage(MultipartFile multipartFile);
}
