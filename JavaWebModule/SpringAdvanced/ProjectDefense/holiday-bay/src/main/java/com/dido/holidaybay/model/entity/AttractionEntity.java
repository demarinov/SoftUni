package com.dido.holidaybay.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="attractions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AttractionEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Lob
    private String content;

    @Lob
    private String additionalInfo;
}
