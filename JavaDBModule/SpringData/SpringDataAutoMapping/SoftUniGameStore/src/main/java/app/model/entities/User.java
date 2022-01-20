package app.model.entities;

import app.model.validators.email.Email;
import app.model.validators.password.Password;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends BaseEntity{

    @Column(unique = true)
    @Email
    private String email;

    @Column
    @Password
    private String password;

    @Column(name="full_name")
    private String fullName;

    @ManyToMany
    @JoinTable(name="users_games",
    joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="game_id", referencedColumnName = "id"))
    private Set<Game> games;

    @OneToMany(mappedBy = "buyer")
    private Set<Order> orders;

    @Column(name="is_admin")
    private boolean isAdmin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
