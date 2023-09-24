package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.models.User;

public interface UsersService {

    User save(User user);

    UserDTO getByID(long ID);

    UserDTO getUser(Authentication authentication);


    void editUser(Authentication authentication, UpdateUserDTO updateUser);

    User editImage(Authentication authentication, MultipartFile multipartFile);

    User getUserByUsername(String login);

    boolean ifUserExist(String login);

    User updateUserPassword(NewPasswordDTO newPasswordDTO, Authentication authentication);
}
