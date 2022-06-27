package com.dido.pathfinder.model.entity;

import com.dido.pathfinder.model.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(name="role")
    @Enumerated(value=EnumType.STRING)
    private RoleEnum name;
}
