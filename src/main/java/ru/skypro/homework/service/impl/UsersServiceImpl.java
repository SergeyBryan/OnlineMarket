package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.exceptions.NotFoundInDataBaseException;
import ru.skypro.homework.mapper.UserMapperImpl;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UsersService;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapperImpl userMapper;
    private final FileServiceImpl fileService;
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    public UsersServiceImpl(UsersRepository usersRepository, UserMapperImpl userMapper, FileServiceImpl fileService, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        if (user != null) {
            logger.debug("Изменения в базе данных по пользователю были изменены, {}", user.getUsername());
            return usersRepository.save(user);
        } else {
            logger.error("Изменения в базе данных по пользователю не были изменены");
            throw new NotFoundException("Внести изменения в базу данных не удалось");
        }
    }

    @Override
    public UserDTO getByID(long ID) {
        User user = usersRepository.findById(ID).orElse(null);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO getUser(Authentication authentication) {
        return userMapper.userToUserDTO(getUserByUsername(authentication.getName()));
    }


    @Override
    public void editUser(Authentication authentication, UpdateUserDTO updateUser) {
        User user = getUserByUsername(authentication.getName());
        if (user != null) {
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhone(updateUser.getPhone());
            save(user);
            logger.debug("Пользователь был успешно обновлён, {}", user);
        }
    }

    @Override
    public User editImage(Authentication authentication, MultipartFile multipartFile) {
        User user = getUserByUsername(authentication.getName());
        String image = fileService.saveImage(multipartFile);
        user.setImage("/" + image);
        logger.debug("Изображение пользователя обновлено, {}", multipartFile.getOriginalFilename());
        return save(user);
    }


    @Override
    public User getUserByUsername(String login) {
        return usersRepository.findUserByUsername(login).orElseThrow(NotFoundInDataBaseException::new);
    }

    @Override
    public boolean ifUserExist(String login) {
        return usersRepository.existsUserByUsername(login);
    }

    @Override
    public User updateUserPassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        User user = getUserByUsername(authentication.getName());
        user.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
        logger.debug("Пароль был обновлён");
        return save(user);
    }
}
