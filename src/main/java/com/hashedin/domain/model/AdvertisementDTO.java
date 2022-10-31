package com.hashedin.domain.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdvertisementDTO {

  public Long id;

  @NotBlank(message = "Title is required")
  private String title;


  @NotBlank(message = "Content is required")
  private String content;

  @NotBlank
  @Digits(integer = 10, fraction = 0, message = "10 digit phone number")
  private String contactNo;

}

