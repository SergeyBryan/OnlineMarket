package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.comment.ListCommentsDTO;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.exceptions.NotFoundInDataBaseException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comment create(User user, Ad ad, CreateOrUpdateCommentDTO dto) {
        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setCommentAuthor(user);
        comment.setText(dto.getText());
        return commentRepository.save(comment);
    }

    @Override
    public Comment get(Long id) {
        if (id != null) {
            return commentRepository.findById(id).orElseThrow(NotFoundInDataBaseException::new);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            logger.debug("Комментарий удалён, {}", id);
            commentRepository.deleteById(id);
        } else {
            logger.error("Ошибка при удалении комментария");
            throw new NotFoundException();
        }
    }

    @Override
    public Comment patch(Comment comment) {
        if (comment != null) {
            logger.debug("Коммент создан, {}", comment.getText());
            return commentRepository.save(comment);
        } else {
            logger.error("Ошибка при создании комментария");
            throw new NotFoundException();
        }
    }

    @Override
    public Comment update(Long id, CreateOrUpdateCommentDTO dto) {
        Comment comment = get(id);
        if (comment != null) {
            comment.setText(dto.getText());
            return commentRepository.save(comment);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public ListCommentsDTO findCommentsByAdId(Long id) {
        return commentMapper.commentsToListCommentsDTO(commentRepository.findAllByAdId(id));

    }
}
