package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherDto {

    private Long id;

    private String name;

    private BookingDto booking;

    private LocalDateTime endDate;

    private String imageUrl;

    private boolean hasExpired;

    private LocalDateTime created;
    private LocalDateTime modified;
}
