package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.Ad;

import javax.transaction.Transactional;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    @Transactional
    List<Ad> getByAuthorId(Long authorId);
}
