package com.dido.holidaybay.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="vouchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VoucherEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @OneToOne
    @JoinColumn(name="booking_id", referencedColumnName = "id")
    private BookingEntity booking;


    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="has_expired")
    private boolean hasExpired;

    @Column(name="image_url")
    private String imageUrl;
}
