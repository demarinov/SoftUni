package com.dido.pathfinder.model.entity;

import com.dido.pathfinder.model.LevelEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class UserEntity extends BaseEntity {
    // •	username - Accepts String values
    @Column(name="username")
    private String username;
    //o	Accepts values, which should be at least 2 characters
    //•	password - Accepts String values

    @Column(name="password")
    @Size(min=2)
    private String password;
    //o	Accepts values, which should be at least 2 characters
    //•	email - Accepts String values

    @Column(name="age")
    private Integer age;

    @Column(name="fullname")
    private String fullName;

    @Email
    @Column(name="email")
    private String email;
    //o	Accepts values, which contain the '@' symbol
    //•	role - Accepts Role Entity values
    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name="users_roles",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<RoleEntity> roles;
    //o	Each registered user should have a "User" role
    //•	level - Accepts a level of the user (BEGINNER, INTERMEDIATE, ADVANCED)
    @Column(name="level")
    @Enumerated(value=EnumType.STRING)
    private LevelEnum level;
}
