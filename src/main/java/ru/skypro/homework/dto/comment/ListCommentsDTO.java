package ru.skypro.homework.dto.comment;

import lombok.Data;

import java.util.List;

@Data
public class ListCommentsDTO {
    int count;
    List<CommentDTO> result;
}
