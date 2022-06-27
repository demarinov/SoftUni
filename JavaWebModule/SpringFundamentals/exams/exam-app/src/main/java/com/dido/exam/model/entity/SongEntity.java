package com.dido.exam.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="songs")
@Getter
@Setter
public class SongEntity extends BaseEntity {
    //•	Has an Id – UUID-String or Long
    //•	Has a Performer (unique, not null)
    //o	Performer name length must be between 3 and 20 characters (inclusive of 3 and 20).
    //•	Has a Title (not null)
    //o	Title length must be between 2 and 20 characters (inclusive of 2 and 20).
    //•	Has a Duration (not null)
    //o	The duration must be a positive number
    //•	Has a Release date (optional)
    //o	The Date that cannot be in the future
    //•	Has a Style (not null)
    //o	One song has one style and one style can have many songs.

    @Column(name="performer", unique = true)
    @Size(min=3, max=20)
    private String performer;

    @Column(name="title")
    @Size(min=2, max=20)
    private String title;

    @Column(name="duration")
    @Positive
    @NotNull
    private Long duration;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name="style_id", referencedColumnName = "id")
    private StyleEntity style;

}
