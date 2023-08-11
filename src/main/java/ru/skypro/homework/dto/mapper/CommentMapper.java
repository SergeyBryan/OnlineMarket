package ru.skypro.homework.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.ListCommentsDTO;
import ru.skypro.homework.models.Comments;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "ad", ignore = true)
    Comments commentDTOToComments(CommentDTO commentDTO);

//    @Mapping(target = "ad", ignore = true)
    CommentDTO commentsToCommentDTO(Comments comment);

    default ListCommentsDTO commentsToListCommentsDTO(List<Comments> commentsList) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentsList.forEach(e -> commentDTOList.add(commentsToCommentDTO(e)));

        ListCommentsDTO listCommentsDTO = new ListCommentsDTO();
        listCommentsDTO.setResult(commentDTOList);
        listCommentsDTO.setCount(commentsList.size());

        return listCommentsDTO;
    }
}
