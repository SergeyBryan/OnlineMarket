package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrUpdateAdDTO {
    private String price;
    private String title;
    private String description;
}
