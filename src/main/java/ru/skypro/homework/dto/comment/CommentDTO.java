package ru.skypro.homework.dto.comment;

import lombok.Data;
import ru.skypro.homework.models.User;

@Data
public class CommentDTO {
    private Long id;
    private User author;
    private int createAt;
    private String text;
}
