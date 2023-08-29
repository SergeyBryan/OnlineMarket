package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapperImpl;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UsersService;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapperImpl userMapper;

    public UsersServiceImpl(UsersRepository usersRepository, UserMapperImpl userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        if (user != null) {
            usersRepository.save(user);
        }
    }

    @Override
    public UserDTO getByID(int ID) {
        User user = usersRepository.findById(ID).orElse(null);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO getUser(String login) {
        User user = getUserByLogin(login);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return usersRepository.findUserByLogin(login);
    }

    @Override
    public void editUser(String login, UpdateUserDTO updateUser) {
        User user = getUserByLogin(login);
        if (user != null && getByID(user.getID()) != null) {
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhone(updateUser.getPhone());
            save(user);
        } else {
            throw new NotFoundException("Пользователь не был найден или ещё не существует");
        }
    }

    @Override
    public void editImage(MultipartFile multipartFile) {
    }


    @Override
    public User getUserByUsername(String username) {
        return usersRepository.getByEmail(username);
    }
}
