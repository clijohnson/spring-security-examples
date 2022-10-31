package com.hashedin.domain.mapper;

import com.hashedin.domain.entity.Advertisement;
import com.hashedin.domain.model.AdvertisementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AdvertisementMapper {

  Advertisement toEntity(AdvertisementDTO advertisementDTO);

  AdvertisementDTO toDTO(Advertisement advertisement);
}
