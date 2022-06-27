package com.dido.pathfinder.model.entity;

import com.dido.pathfinder.model.RouteLevelEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="routes")
@Getter
@Setter
public class RouteEntity extends BaseEntity {

    @Column(name="description")
    @Lob
    private String description;

    // •	gpx coordinates - Accepts very long text values
    @Column(name="gpx_coordinates")
    @Lob
    private String gpx;
    //•	level - Accepts the levels of the routes (BEGINNER, INTERMEDIATE, ADVANCED) as values
    @Column(name="level")
    @Enumerated(value=EnumType.STRING)
    private RouteLevelEnum level;

    //•	name - Accepts String values
    @Column(name="name")
    private String name;
    //•	author - Accepts User Entities as values
    @ManyToOne()
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private UserEntity author;
    //•	video url – Accepts the ids of youtube videos as values
    @Column(name="video_url")
    private String videoUrl;

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="routes_categories",
        joinColumns = @JoinColumn(name="route_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "id")
    )
    private Set<CategoriesEntity> categories;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<CommentsEntity> comments;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<PicturesEntity> pictures;
}
