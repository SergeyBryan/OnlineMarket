package ru.skypro.homework.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.HomeworkApplication;
import ru.skypro.homework.dto.auth.Register;
import ru.skypro.homework.dto.auth.Role;
import ru.skypro.homework.service.AuthService;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("online_reselling_tests")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HomeworkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthServiceTest {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthService authService;

    @BeforeEach
    public void clear() {
        usersRepository.deleteAll();
    }

    @Test
    public void testRegistration() {
        Register register = new Register();
        register.setFirstName("Test");
        register.setRole(Role.USER);
        register.setLastName("TestName");
        register.setPhone("+7(904)510-05-96");
        register.setUsername("test@mail.ru");
        register.setPassword("password");

        assertTrue(authService.register(register));
        assertTrue(authService.login("test@mail.ru", "password"));
        assertTrue(usersRepository.existsUserByUsername("test@mail.ru"));
    }

    @Test
    public void testRegistrationAndAuthorizationWithWrongData() {
        Register register = new Register();
        register.setFirstName("Test");
        register.setRole(Role.USER);
        register.setLastName("TestName");
        register.setPhone("+7(904)510-05-96");
        register.setUsername("test@mail.ru");
        register.setPassword("password");

        assertTrue(authService.register(register));
        assertTrue(usersRepository.existsUserByUsername("test@mail.ru"));
        assertFalse(authService.login("test@mail.ru", "wrongPassword"));
    }
}
