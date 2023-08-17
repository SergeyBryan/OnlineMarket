package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.ListCommentsDTO;
import ru.skypro.homework.models.Comment;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "ad", ignore = true)
    Comment commentDTOToComments(CommentDTO commentDTO);

    CommentDTO commentsToCommentDTO(Comment comment);

    default ListCommentsDTO commentsToListCommentsDTO(List<Comment> commentsList) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentsList.forEach(e -> commentDTOList.add(commentsToCommentDTO(e)));

        ListCommentsDTO listCommentsDTO = new ListCommentsDTO();
        listCommentsDTO.setResult(commentDTOList);
        listCommentsDTO.setCount(commentsList.size());

        return listCommentsDTO;
    }
}
