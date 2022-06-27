package com.dido.exam.model.entity;

import com.dido.exam.model.enums.StyleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="styles")
@Getter
@Setter
public class StyleEntity extends BaseEntity {

    // •	Has an Id – UUID-string or Long
    //•	Has a Style name (unique, not null)
    //o	an option between (POP, ROCK, JAZZ)
    //•	Has a Description (optional)

    @Column(name="name", unique = true)
    @NotEmpty
    private String name;

    @Column(name="option")
    @Enumerated(EnumType.STRING)
    private StyleEnum option;

    @Column(name="description")
    private String description;
}
