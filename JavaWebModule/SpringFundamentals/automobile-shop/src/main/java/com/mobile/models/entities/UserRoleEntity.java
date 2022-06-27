package com.mobile.models.entities;

import com.mobile.models.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {

    // •	id – uuid or number.
    //•	role –  enumerated value.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
