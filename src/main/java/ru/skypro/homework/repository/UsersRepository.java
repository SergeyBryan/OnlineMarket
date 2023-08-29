package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    User findUserByLogin(String login);

}
