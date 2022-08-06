package com.dido.holidaybay.model.dto;

import com.dido.holidaybay.model.validation.DatePast;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    private Long id;

    @NotNull
    @Min(1)
    private Long roomId;

    @NotNull
    @Min(1)
    private Long durationInNights;

    // 5% of roomPrice
    @NotNull
    @Positive
    private double price;

    private String imageUrl;

    private boolean active;

    @NotNull
    @DatePast
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
}
