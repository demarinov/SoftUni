package com.dido.holidaybay.model.entity;

import com.dido.holidaybay.model.enums.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
