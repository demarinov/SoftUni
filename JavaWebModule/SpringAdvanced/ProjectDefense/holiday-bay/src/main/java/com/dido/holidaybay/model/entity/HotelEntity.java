package com.dido.holidaybay.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="hotels")
public class HotelEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "hotel")
    private List<RoomEntity> rooms;
}
