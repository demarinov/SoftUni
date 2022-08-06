package com.dido.holidaybay.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="bookings")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class BookingEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name="booked_room_id")
    private Long roomId;

    @Column(name="duration_in_nights")
    private Long durationInNights;

    // 5% of roomPrice
    @Column(name="price")
    private double price;

    @Column(name="active")
    private boolean active;

    @Column(name="start_date")
    private LocalDate startDate;
}
