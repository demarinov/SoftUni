package com.dido.battleships.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="ships")
@Getter
@Setter
public class ShipEntity extends BaseEntity {

    // •	Id – Accepts UUID-String or Long values
    //•	Name
    //o	The length of the values must be between 2 and 10 characters (both numbers are INCLUSIVE)
    //o	The values should be unique in the database

    @Column(name="name", unique = true)
    @Size(min=2, max=10)
    @NotNull
    private String name;
    //
    //•	Health
    //o	The values should be positive numbers

    @Column(name="health")
    @Positive
    @NotNull
    private Integer health;
    //•	Power
    //o	The values should be positive numbers

    @Column(name="power")
    @Positive
    @NotNull
    private Integer power;
    //•	Created
    //o	The values should not be future dates

    @Column(name="created")
    private LocalDate created;
    //•	Category
    //o	A relationship with the Categories Entity

    @ManyToOne()
    @JoinColumn(name= "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    //•	User
    //o	A user that owns the ship
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity user;
}
