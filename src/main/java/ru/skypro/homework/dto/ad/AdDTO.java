package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class AdDTO {
    private Long id;
    private Long idUser;
    private String image;
    private int price;
    private String title;
}
