package com.dido.battleships.model.dto;

import com.dido.battleships.model.enums.ShipEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotEmpty
    private ShipEnum name;

    @NotEmpty
    private String description;
}
