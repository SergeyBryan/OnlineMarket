package ru.skypro.homework.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDto;
import ru.skypro.homework.dto.ad.ListAdsDTO;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.User;

import java.io.IOException;


public interface AdService {
    ListAdsDTO getAllAds();

    AdDTO addAd(User user, CreateOrUpdateAdDTO adDTO, MultipartFile image) throws IOException;

    AdDTO getAd(Long id) throws NotFoundException;

    void deleteAd(String id);

    AdDTO updateAd(CreateOrUpdateAdDTO adDTO, Long id);

    ListAdsDTO getUsersAds(String userid);

    InputStreamResource updateAdsImage(String id, MultipartFile image) throws IOException;


}
