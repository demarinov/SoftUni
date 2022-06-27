package com.dido.pathfinder.model.entity;

import com.dido.pathfinder.model.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class CategoriesEntity extends BaseEntity {

    // •	name - Accepts String values (PEDESTRIAN, BICYCLE, MOTORCYCLE, CAR)
    @Enumerated(EnumType.STRING)
    @Column(name="name")
    private CategoryEnum name;

    //•	description - Accepts very long String values
    @Column(name="description")
    private String description;
}
