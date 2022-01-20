package app.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    @OneToMany(mappedBy = "townLiving")
    private Set<User> usersLiving;

    @OneToMany(mappedBy = "townBorn")
    private Set<User> usersBorn;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getUsersLiving() {
        return usersLiving;
    }

    public void setUsersLiving(Set<User> usersLiving) {
        this.usersLiving = usersLiving;
    }

    public Set<User> getUsersBorn() {
        return usersBorn;
    }

    public void setUsersBorn(Set<User> usersBorn) {
        this.usersBorn = usersBorn;
    }
}
