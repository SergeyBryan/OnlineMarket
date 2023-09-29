package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ListAdsDTO;
import ru.skypro.homework.models.Ad;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "author.id", target = "idUser")
    @Mapping(source = "id", target = "id")
    AdDTO toAdDTO(Ad entity);

    Ad toAd(AdDTO dto);




    CreateOrUpdateAdDTO toCreateOrUpdateAdDTO(Ad ad);

    Ad createOrUpdateDTOToAd(CreateOrUpdateAdDTO ad);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdFromDto(CreateOrUpdateAdDTO dto, @MappingTarget Ad entity);

    default ListAdsDTO toListAdsDTO(List<Ad> adList) {
        List<CreateOrUpdateAdDTO> results = adList.stream()
                .map(this::toCreateOrUpdateAdDTO)
                .collect(Collectors.toList());
        return new ListAdsDTO(results.size(), results);
    }


}