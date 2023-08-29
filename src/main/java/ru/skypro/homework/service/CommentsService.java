package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.comment.ListCommentsDTO;
import ru.skypro.homework.exceptions.NotFoundException;

public interface CommentsService {

    ListCommentsDTO getAllComments(String adId);

    CommentDTO addCommentToAd(String adId, CreateOrUpdateCommentDTO createCommentDTO) throws NotFoundException;

    void deleteComment(String commentId);

    CommentDTO updateComment(String commentId, CreateOrUpdateCommentDTO updateCommentDTO);
}
