package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.models.User;

public interface UsersService {

    User save(User user);

    User getByID(int ID);

    UpdateUserDTO editUser(UpdateUserDTO updateUser);

    void editImage(MultipartFile multipartFile);
}
