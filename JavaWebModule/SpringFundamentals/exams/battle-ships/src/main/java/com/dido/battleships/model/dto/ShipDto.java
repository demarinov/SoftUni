package com.dido.battleships.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipDto {

    private Long id;

    @Size(min=2, max=10)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Integer health;

    @Positive
    @NotNull
    private Integer power;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate created;

    @NotNull
    private CategoryDto category;

    @Override
    public String toString() {
        return String.format("%s -- %d -- %d", name, health, power);
    }
}
