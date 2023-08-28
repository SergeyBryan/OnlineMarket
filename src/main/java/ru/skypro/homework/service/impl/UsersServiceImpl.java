package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UsersService;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User getByID(int ID) {
        return null;
    }

    @Override
    public UpdateUserDTO editUser(UpdateUserDTO updateUser) {
        return new UpdateUserDTO();
    }

    @Override
    public void editImage(MultipartFile multipartFile) {

    }

    @Override
    public User getUserByUsername(String username) {
        return usersRepository.getByEmail(username);
    }
}
