package ru.skypro.homework.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.HomeworkApplication;
import ru.skypro.homework.dto.auth.Role;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("online_reselling_tests")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HomeworkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdServiceTest {

    @Autowired
    private AdRepository adEntityRepo;

    @Autowired
    private UsersRepository userEntityRepo;
    private final List<Ad> ads = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    private final User userEntity = new User(1L, "test@mail.ru", "password",
            "Test", "TestName", "+7(904)510-05-96",
            Role.USER, "/images/users/753c8737-a9f4-46fe-b038-2e1990bdb492.jpeg");

    private final Ad adEntity1 = new Ad();

    private final Ad adEntity2 = new Ad();

    private final List<Ad> adEntities = List.of(adEntity1, adEntity2);

    {
        adEntities.forEach(adEntity -> {
            adEntity.setImage("image.jpg");
            adEntity.setPrice(999);
            adEntity.setAuthor(null);
            adEntity.setTitle("Название");
        });
        adEntity1.setId(1L);
        adEntity2.setId(2L);
    }

    @BeforeEach
    void setUp() {
        userEntityRepo.save(userEntity);
        adEntityRepo.save(adEntity1);
        adEntityRepo.save(adEntity2);
    }

    @Test
    void shouldReturnListOfAdEntities() {
        List<Ad> adEntities = adEntityRepo.findAll();
        assertEquals(2, adEntities.size());
    }
}
