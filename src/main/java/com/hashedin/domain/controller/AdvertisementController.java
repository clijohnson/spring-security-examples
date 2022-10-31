package com.hashedin.domain.controller;

import com.hashedin.domain.model.AdvertisementDTO;
import com.hashedin.domain.service.AdvertisementService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

  @Autowired
  AdvertisementService advertisementService;

  @PreAuthorize("hasAuthority('READ_ADVERTISEMENT')")
  @GetMapping("/{id}")
  public ResponseEntity<AdvertisementDTO> getAdvertisement(@PathVariable Long id) {
    return ResponseEntity.ok().body(advertisementService.getAdvertisement(id));
  }

  @PreAuthorize("hasAuthority('WRITE_ADVERTISEMENT')")
  @PostMapping
  public ResponseEntity<AdvertisementDTO> postAdvertisement(@RequestHeader String host, @RequestBody @Valid AdvertisementDTO advertisementDto){
    AdvertisementDTO created = advertisementService.createAdvertisement(advertisementDto);
    return ResponseEntity.created(URI.create(host + "/advertisement/" + created.getId().toString()))
                         .body(created);
  }

  @PreAuthorize("hasAuthority('WRITE_ADVERTISEMENT')")
  @DeleteMapping("/{id}")
  public ResponseEntity<AdvertisementDTO> deleteAdvertisement(@PathVariable Long id){
    return ResponseEntity.accepted().body(advertisementService.deleteAdvertisement(id));
  }
}
