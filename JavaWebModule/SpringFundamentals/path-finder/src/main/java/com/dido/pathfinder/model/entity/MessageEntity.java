package com.dido.pathfinder.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class MessageEntity extends BaseEntity {

    // •	date time - Accepts Date and Time values
    @Column(name="dateTime")
    private LocalDateTime dateTime;
    //•	text content - Accepts very long String values
    @Column(name="text")
    private String textContent;
    //•	author - Accepts User Entities as values

    @ManyToOne()
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private UserEntity author;

    //•	recipient - Accepts User Entities as values

    @ManyToOne()
    @JoinColumn(name="recipient_id", referencedColumnName = "id")
    private UserEntity recipient;
}
