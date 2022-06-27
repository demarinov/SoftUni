package com.dido.battleships.model.entity;

import com.dido.battleships.model.enums.ShipEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Getter
@Setter
public class CategoryEntity extends BaseEntity {

    // •	Id – Accepts UUID-String or Long values
    //•	Name
    @Column(name="name", unique = true)
    @Enumerated(EnumType.STRING)
    private ShipEnum name;
    //o	An option between BATTLE, CARGO, PATROL
    //o	The values should be unique in the database
    //•	Description
    //o	A very long and detailed description of the category
    //o	Can accept null values

    @Column(name="description")
    @Lob
    private String description;
}
