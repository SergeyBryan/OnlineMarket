package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.models.User;

public interface UsersService {

    User save(User user);

    UserDTO getByID(long ID);

    UserDTO getUser(UserDetails userDetails);

    User findUserByLogin(String userName);

    void editUser(UserDetails userDetails, UpdateUserDTO updateUser);

    User editImage(UserDetails userDetails, MultipartFile multipartFile);

    User getUserByUsername(String login);

    User updateUserPassword(NewPasswordDTO newPasswordDTO, UserDetails userDetails);
}
