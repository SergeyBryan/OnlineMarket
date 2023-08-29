package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.comment.ListCommentsDTO;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentsService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentMapper mapper;

    private final AdService adService;

    public CommentsServiceImpl(CommentsRepository commentsRepository, CommentMapper mapper, AdService adService) {
        this.commentsRepository = commentsRepository;
        this.mapper = mapper;
        this.adService = adService;
    }

    @Override
    public ListCommentsDTO getAllComments(String adId) {
        return mapper.commentsToListCommentsDTO(commentsRepository.getAllByAd_Id(Long.parseLong(adId)));
    }

    @Override
    public CommentDTO addCommentToAd(String adId, CreateOrUpdateCommentDTO createCommentDTO) throws NotFoundException {
        Ad ad = adService.getAd(adId);
        Comment comment = Comment.builder()
                .text(createCommentDTO.getText())
                .createdAt(
                        ZonedDateTime.of(
                                LocalDateTime.now(),
                                ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli())
                .ad(ad)
                .build();
        return mapper.commentsToCommentDTO(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        commentsRepository.deleteById(Long.parseLong(commentId));
    }

    @Override
    public CommentDTO updateComment(String commentId, CreateOrUpdateCommentDTO updateCommentDTO) {
        Comment comment = commentsRepository.getReferenceById(Long.parseLong(commentId));
        comment.setText(updateCommentDTO.getText());
        return mapper.commentsToCommentDTO(commentsRepository.save(comment));
    }
}
