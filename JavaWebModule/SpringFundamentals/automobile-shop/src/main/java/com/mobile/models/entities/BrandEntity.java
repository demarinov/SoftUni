package com.mobile.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BrandEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "brand", targetEntity = ModelEntity.class, fetch = FetchType.EAGER,
            cascade=CascadeType.ALL)
    private List<ModelEntity> models;
}
