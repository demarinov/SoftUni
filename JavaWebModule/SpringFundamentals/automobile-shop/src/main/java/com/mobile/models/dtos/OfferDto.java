package com.mobile.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OfferDto {

    private UUID id;

    @NotEmpty
    private String description;

    private LocalDateTime created;

    private LocalDateTime modified;

    @NotEmpty
    private String engine;

    @NotEmpty
    private String imageUrl;

    @NotNull
    private Long mileage;

    @NotNull
    private Double price;

    @NotEmpty
    private String transmission;

    @NotNull
    private Long modelId;

    @NotNull
    private Integer year;

    private UserDto seller;

}
