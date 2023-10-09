package ru.skypro.homework.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.HomeworkApplication;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.auth.Role;
import ru.skypro.homework.models.User;
import ru.skypro.homework.service.UsersService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ActiveProfiles("online_reselling_tests")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HomeworkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    public void create() {
        usersRepository.save(userEntity);
    }

    private final User userEntity = new User(1L, "test@mail.ru", "password",
            "Test", "TestName", "+7(904)510-05-96",
            Role.USER, "/images/users/753c8737-a9f4-46fe-b038-2e1990bdb492.jpeg");

    @Test
    public void testSearchingMethods() {
        User userByUsername = usersService.getUserByUsername(userEntity.getUsername());
        UserDTO userById = usersService.getByID(1L);

        assertEquals(userByUsername.getUsername(), userEntity.getUsername());
        assertEquals(userById.getPhone(), userEntity.getPhone());
        assertTrue(usersService.ifUserExist(userEntity.getUsername()));
    }

    @Test
    public void testUpdateMethods() {
        String text = "NewTest";
        Authentication authentication = mock(Authentication.class);
        UpdateUserDTO userDTO = new UpdateUserDTO();
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(text);
        userDTO.setPhone(userEntity.getPhone());

        when(authentication.getName()).thenReturn(userEntity.getUsername());
        usersService.editUser(authentication, userDTO);
        User user = usersService.getUserByUsername(userEntity.getUsername());


        assertEquals(user.getLastName(), text);
    }
}
