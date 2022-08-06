package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherAdminDto {

    @NotNull
    private Long voucherId;
}
