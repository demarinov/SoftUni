package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDto {

    @NotNull
    private Long userId;

    @NotEmpty
    private String roleType;
}
