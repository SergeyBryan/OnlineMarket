package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.User;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Transactional
    Optional<User> findUserByUsername(String username);

    boolean existsUserByUsername(String username);
}
