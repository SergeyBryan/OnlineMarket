package ru.skypro.homework.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ListAdsDTO;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.models.Ad;

import java.io.IOException;


public interface AdService {
    ListAdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO adDTO, MultipartFile image) throws IOException;

    AdDTO getAdDTO(String id) throws NotFoundException;

    Ad getAd(String id) throws NotFoundException;

    void deleteAd(String id);

    AdDTO updateAd(CreateOrUpdateAdDTO adDTO, String id);

    ListAdsDTO getUsersAds(String userid);

    InputStreamResource updateAdsImage(String id, MultipartFile image) throws IOException;
}
