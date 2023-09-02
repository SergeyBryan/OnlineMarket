package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.Register;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UsersService;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UsersService usersService;
    private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder encoder;


    @Override
    public boolean login(String userName, String password) {
        if (!userDetailsManager.userExists(userName)) {
            return false;
        }
        return encoder.matches(password, usersService.getUserByUsername(userName).getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (usersService.ifUserExist(register.getUsername())) {
            return false;
        }
        usersService.save(ru.skypro.homework.models.User.builder().password(encoder.encode(register.getPassword()))
                .email(register.getUsername())
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .phone(register.getPhone()).role(register.getRole())
                .build());
        return true;
    }

}
