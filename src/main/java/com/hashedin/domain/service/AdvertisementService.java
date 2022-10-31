package com.hashedin.domain.service;

import com.hashedin.domain.model.AdvertisementDTO;
import java.net.URI;

public interface AdvertisementService {
  AdvertisementDTO getAdvertisement(Long id);

  AdvertisementDTO createAdvertisement(AdvertisementDTO advertisementDto);

  AdvertisementDTO deleteAdvertisement(Long id);
}
