package com.dido.exam.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    // •	Has an Id – UUID-String or Long
    //•	Has a Username (unique, not null)
    //o	Username length must be between 3 and 20 characters (inclusive of 3 and 20).
    //•	Has a Password (not null)
    //o	Password length must be between 3 and 20 characters (inclusive of 3 and 20).
    //•	Has an Email (unique, not null)
    //o	Must contain '@'.
    //•	Has a Playlist
    //o	The playlist contains songs.
    // One user may have many songs and one song can be saved by many users to their playlist.


    // •	username - Accepts String values
    @Column(name="username", unique = true)
    @NotEmpty
    private String username;

    @Column(name="password")
    @NotEmpty
    private String password;

    @Column(name="email", unique = true)
    @NotEmpty
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name="users_songs",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="song_id", referencedColumnName = "id"))
    private List<SongEntity> playlist;
}
