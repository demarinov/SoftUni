package com.dido.holidaybay.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {


    // •	id – uuid or number.
    //•	username –  username of the user.
    //•	password – password of the user.
    //•	firstName –  first name of the user.
    //•	lastName –  last name of the user.
    //•	isActive – true OR false.
    //•	role –  user's role (User or Admin).
    //•	imageUrl – a url of user's picture.
    //•	created – a date and time.
    //•	modified – a date and time.


    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name="age")
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<UserRoleEntity> userRoles;


    @OneToMany(mappedBy = "user")
    private List<BonusEntity> bonuses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankAccount bankAccount;

}
