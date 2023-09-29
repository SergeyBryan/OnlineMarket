package ru.skypro.homework.service.impl;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
//import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTODTO;
import ru.skypro.homework.dto.ad.ExtendedAdDto;
//import ru.skypro.homework.dto.ad.ExtendedAdDtoDto;
import ru.skypro.homework.dto.ad.ListAdsDTO;
//import ru.skypro.homework.dto.ad.ListListAdsDTODTO;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
//import ru.skypro.homework.service.ListAdsDTOervice;
import ru.skypro.homework.service.FileService;
import ru.skypro.homework.service.UsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper mapper;
    private final FileService fileService;
    private final UsersService userService;

    public AdServiceImpl(AdRepository adRepository, AdMapper mapper, FileService fileService, UsersService userService) {
        this.adRepository = adRepository;
        this.mapper = mapper;
        this.fileService = fileService;
        this.userService = userService;
    }

    @Override
    public ListAdsDTO getAllAds() {
        List<Ad> all = adRepository.findAll();
        return mapper.toListAdsDTO(all);
    }

    @Override
    public AdDTO addAd(User user, CreateOrUpdateAdDTO CreateOrUpdateAdDTODTO, MultipartFile image) {
        Ad ad = mapper.createOrUpdateDTOToAd(CreateOrUpdateAdDTODTO);
        ad.setImage(fileService.saveImage(image));
        ad.setImage(fileService.saveAdsImage(image));
        ad.setAuthor(user);
        return mapper.toAdDTO(adRepository.save(ad));
    }

    @Override
    public AdDTO getAd(Long id) throws NotFoundException {
        Optional<Ad> adById = adRepository.findById(id);
        if (adById.isPresent()) {
            return mapper.toAdDTO(adById.get());
        } else {
            throw new NotFoundException("Объявление id:" + id + " не найдено");
        }
    }

    @Override
    public void deleteAd(String id) {
        adRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public AdDTO updateAd(CreateOrUpdateAdDTO adDTO, Long id) {
        Ad byId = adRepository.getReferenceById(id);
        mapper.updateAdFromDto(adDTO, byId);
        adRepository.save(byId);
        return null;
    }

    @Override
    public ListAdsDTO getUsersAds(String username) {
        var user = userService.getUserByUsername(username);
        if (user != null) {
            List<Ad> byAuthorId = adRepository.getByAuthorId(user.getId());
            return mapper.toListAdsDTO(byAuthorId);
        } else {
            return new ListAdsDTO();
        }
    }

    @Override
    public InputStreamResource updateAdsImage(String id, MultipartFile image) throws IOException {
        Ad byId = adRepository.getReferenceById(Long.parseLong(id));
        String pathToImage = byId.getImage();
        fileService.updateImage(pathToImage, image);
        FileInputStream inputStream = new FileInputStream(pathToImage);
        return new InputStreamResource(inputStream);
    }
}
