package app.models;

import app.annotations.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username", nullable = false)
    @Size(min=4, max=30)
    private String userName;

    @Column(name="password", nullable = false)
    @Size(min=6 , max=50)
    private String password;

    @Email
    @Column(name="email", nullable = false)
    private String email;

    @Column(name="registered_on")
    private Date registeredOn;

    @Column(name="last_time_logged_in")
    private Date lastTimeLoggedIn;

    @Column(name="age")
    @Min(1)
    @Max(120)
    private int age;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Album> albums;

    @ManyToOne
    private Town townLiving;

    @ManyToOne
    private Town townBorn;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name="users_users",
    joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="friend_id", referencedColumnName = "id"))
    private Set<User> friends;


    @ManyToMany(mappedBy = "friends")
    private Set<User> users;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Town getTownLiving() {
        return townLiving;
    }

    public void setTownLiving(Town townLiving) {
        this.townLiving = townLiving;
    }

    public Town getTownBorn() {
        return townBorn;
    }

    public void setTownBorn(Town townBorn) {
        this.townBorn = townBorn;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
