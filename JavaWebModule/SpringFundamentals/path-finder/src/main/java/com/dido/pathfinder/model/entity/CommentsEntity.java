package com.dido.pathfinder.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class CommentsEntity extends BaseEntity {

    // •	approved - Accepts boolean values
    @Column(name="approved")
    private Boolean approved;
    //•	created - Accepts Date and Time values

    @Column(name="created")
    private LocalDateTime created;
    //o	 The values should not be future dates
    //•	text content - Accepts very long text values

    @Column(name="text")
    private String textContent;

    //•	author - Accepts User Entities as values

    @ManyToOne()
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private UserEntity author;
    //•	route - Accepts Route Entities as values

    @ManyToOne()
    @JoinColumn(name="route_id", referencedColumnName = "id")
    private RouteEntity route;
}
