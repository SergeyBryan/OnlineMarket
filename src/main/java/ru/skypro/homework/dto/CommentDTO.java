package ru.skypro.homework.dto;

import ru.skypro.homework.models.User;

public class CommentDTO {
    private int id;
    private User author;
    private int createAt;
    private String text;
}
