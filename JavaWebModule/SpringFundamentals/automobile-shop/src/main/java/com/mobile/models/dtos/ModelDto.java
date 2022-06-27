package com.mobile.models.dtos;

import com.mobile.models.enums.CategoryEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelDto {
    private Long id;

    @NotEmpty
    private String name;

    private CategoryEnum category;

    private String imageUrl;

    private Long startYear;

    private Long endYear;

    private LocalDateTime created;

    private LocalDateTime modified;

    private BrandDto brand;

}
