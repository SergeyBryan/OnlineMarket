package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.comment.ListCommentsDTO;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.User;

import java.util.List;

public interface CommentService {

    Comment create(User user, Ad ad, CreateOrUpdateCommentDTO dto);

    Comment get(Long id);

    void delete(Long id);

    Comment patch(Comment comment);

    Comment update(Long id, CreateOrUpdateCommentDTO dto);

    ListCommentsDTO findCommentsByAdId(Long id);

}
