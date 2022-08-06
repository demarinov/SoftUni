package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonusDto {

    private Long id;

    @NotEmpty
    private String bonusType;

    private String info;

    private boolean notGiven;

    private LocalDateTime created;
}
