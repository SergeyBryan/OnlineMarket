package ru.skypro.homework.models;

import lombok.Data;
import ru.skypro.homework.dto.ad.AdDTO;

@Data
public class Comments {
    private int id;
    private String text;
    private Long createdAt;
    private User author;
    private AdDTO ad;
}
