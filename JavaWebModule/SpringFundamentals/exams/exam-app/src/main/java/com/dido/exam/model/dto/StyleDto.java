package com.dido.exam.model.dto;

import com.dido.exam.model.enums.StyleEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StyleDto {

    @NotEmpty
    private String name;

    private StyleEnum option;

    private String description;
}
