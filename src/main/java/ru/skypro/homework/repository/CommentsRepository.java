package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.Comment;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> getAllByAd_Id(Long adId);
}
