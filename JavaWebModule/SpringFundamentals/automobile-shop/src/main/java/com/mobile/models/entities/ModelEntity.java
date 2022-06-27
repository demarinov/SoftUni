package com.mobile.models.entities;

import com.mobile.models.enums.CategoryEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ModelEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(name="image_url")
    @Size(min=8, max=512)
    private String imageUrl;

    @Column(name="start_year")
    private Long startYear;

    @Column(name="end_year")
    private Long endYear;

    @ManyToOne()
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private BrandEntity brand;
}
