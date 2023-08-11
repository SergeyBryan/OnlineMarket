package ru.skypro.homework.models;

import lombok.Data;

@Data
public class Comments {
    private int id;
    private String text;
    private Long createdAt;
    private User author;
    private Advertisement ad;
}
