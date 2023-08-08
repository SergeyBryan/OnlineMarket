package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListAdsDTO {
    private int count;
    private List<CreateOrUpdateAdDTO> results;
}
