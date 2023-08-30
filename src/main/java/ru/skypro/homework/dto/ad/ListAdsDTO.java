package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdsDTO {
    private int count;
    private List<CreateOrUpdateAdDTO> results;
}
