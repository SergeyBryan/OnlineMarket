package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdDTO {
    private int ID;
    private int idUser;
    private String image;
    private int price;
    private String title;
}
