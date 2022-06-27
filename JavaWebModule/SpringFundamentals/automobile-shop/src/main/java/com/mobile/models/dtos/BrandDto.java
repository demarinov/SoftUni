package com.mobile.models.dtos;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDto {

    private Long id;

    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

    private List<ModelDto> models;
}
