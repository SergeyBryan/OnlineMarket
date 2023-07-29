package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.Users;
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
    public Users save(Users user) {
        return null;
    }

    @Override
    public Users getByID(int ID) {
        return null;
    }

    @Override
    public String[] editUser(String... newValue) {
        return new String[0];
    }

    @Override
    public void editImage(MultipartFile multipartFile) {

    }
}
