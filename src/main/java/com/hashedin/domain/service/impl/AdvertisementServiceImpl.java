package com.hashedin.domain.service.impl;

import com.hashedin.domain.entity.Advertisement;
import com.hashedin.domain.mapper.AdvertisementMapper;
import com.hashedin.domain.model.AdvertisementDTO;
import com.hashedin.domain.repository.AdvertisementRepository;
import com.hashedin.domain.service.AdvertisementService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

  @Autowired
  private AdvertisementRepository advertisementRepository;

  @Autowired
  private AdvertisementMapper advertisementMapper;

  @Override
  public AdvertisementDTO getAdvertisement(Long id) {
    return advertisementMapper.toDTO(advertisementRepository.findById(id).orElseThrow());
  }

  @Override
  public AdvertisementDTO createAdvertisement(AdvertisementDTO advertisementDto) {
    return advertisementMapper.toDTO(advertisementRepository.save(advertisementMapper.toEntity(advertisementDto)));
  }

  @Override
  @Transactional
  public AdvertisementDTO deleteAdvertisement(Long id) {
    Advertisement advertisement = advertisementRepository.findById(id).orElseThrow();
    advertisementRepository.delete(advertisement);
    return advertisementMapper.toDTO(advertisement);
  }
}
