package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDto {

    @NotNull
    @Min(1)
    private Double depositAmount;
}
