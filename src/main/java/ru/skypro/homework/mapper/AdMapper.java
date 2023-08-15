package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.models.Ad;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "author.id", target = "idUser")
    @Mapping(source = "id", target = "ID")
    AdDTO adToAdDTO(Ad entity);

    Ad adDTOToAd(AdDTO dto);

    List<AdDTO> userToUserDTOList(List<Ad> entities);

    List<Ad> userDTOToUserList(List<AdDTO> dto);
}