package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankingDto {

    @NotNull
    @Min(1)
    private Double depositAmount;

    @NotNull
    @Min(1)
    private Double withdrawAmount;
}
