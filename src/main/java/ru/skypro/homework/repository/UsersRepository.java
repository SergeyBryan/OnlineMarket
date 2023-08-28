package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.User;

import javax.transaction.Transactional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    @Transactional
    User getByEmail(String email);
}
