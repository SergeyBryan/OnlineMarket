package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.models.User;

public interface UsersService {

    void save(User user);

    UserDTO getByID(int ID);

    UserDTO getUser(String login);

    User getUserByFirstName(String login);

    void editUser(String login, UpdateUserDTO updateUser);

    void editImage(MultipartFile multipartFile);

    User getUserByUsername(String username);
}
