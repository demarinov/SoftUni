package com.mobile.models.entities;

import com.mobile.models.enums.EngineEnum;
import com.mobile.models.enums.TransmissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="offers")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class OfferEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy="org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
//    @GeneratedValue
//    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name="description")
    @Lob
    private String description;

    @Column(name="engine")
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="mileage")
    private Long mileage;

    @Column(name="price")
    private Double price;

    @Column(name="transmission")
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    @Column(name="year")
    private Integer year;

    @Column(name="created")
    @NotNull
    private LocalDateTime created;

    @Column(name="modified")
    @NotNull
    private LocalDateTime modified;

    @ManyToOne()
    @JoinColumn(name="model_id", referencedColumnName = "id")
    private ModelEntity model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity seller;
}
