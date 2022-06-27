package com.dido.pathfinder.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="pictures")
@Getter
@Setter
public class PicturesEntity extends BaseEntity {
    //•	title - Accepts String values

    @Column(name="title")
    private String title;
    //•	url - Accepts very long String values

    @Column(name="url")
    private String url;
    //•	author - Accepts User Entities as values

    @ManyToOne()
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private UserEntity author;

    //•	route - Accepts Route Entities as values
    @ManyToOne()
    @JoinColumn(name="route_id", referencedColumnName = "id")
    private RouteEntity route;

}
