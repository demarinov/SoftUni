package com.dido.battleships.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    // •	Id – Accepts UUID-String or Long values
    //•	Username
    //o	The length of the values should be between 3 and 10 characters long (both numbers are INCLUSIVE)
    //o	The values should be unique in the database
    //•	Full Name
    //o	The length of the values should be between 5 and 20 characters long (both numbers are INCLUSIVE)
    //•	Password
    //o	The length of the values should be more than 3 characters long (INCLUSIVE)
    //•	Email
    //o	The values should contain a '@' symbol
    //o	The values should be unique in the database


    // •	username - Accepts String values
    @Column(name="username")
    @NotEmpty
    private String username;
    //o	Accepts values, which should be at least 2 characters
    //•	password - Accepts String values

    @Column(name="password")
    @Size(min=2)
    @NotEmpty
    private String password;
    //o	Accepts values, which should be at least 2 characters
    //•	email - Accepts String values

    @Column(name="full_name")
    @NotEmpty
    private String fullName;

    @Email
    @Column(name="email")
    @NotEmpty
    private String email;
}
