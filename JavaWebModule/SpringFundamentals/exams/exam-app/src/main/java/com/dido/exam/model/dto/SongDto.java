package com.dido.exam.model.dto;

import com.dido.exam.model.entity.StyleEntity;
import com.dido.exam.model.enums.StyleEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {

    private Long id;

    @Size(min=3, max=20)
    private String performer;

    @Size(min=2, max=20)
    private String title;

    @Positive
    @NotNull
    private Long duration;

    private String durationStr;

    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate releaseDate;

    @NotNull
    private StyleEnum style;
}
