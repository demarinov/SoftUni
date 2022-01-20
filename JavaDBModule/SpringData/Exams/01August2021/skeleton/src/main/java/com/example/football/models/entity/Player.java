package com.example.football.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="players")
public class Player extends BaseEntity{

//    •	id – accepts integer values, a primary identification field, an auto incremented field.
//•	first name – accepts char sequences as values where their character length value higher than 2.
    @Column(name="first_name", nullable = false)
    @Size(min=3)
    private String firstName;
//•	last name – accepts char sequences as values where their character length value higher than 2.
    @Column(name="last_name", nullable = false)
    @Size(min=3)
    private String lastName;
//•	email – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique
// in the database.
    @Column(name="email", nullable = false)
    @Email
    private String email;
//•	birth date – a date in the "dd/MM/yyyy" format.

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;
//•	position – one of the following – ATT, MID, DEF.
    @Enumerated
    @Column(name="position", nullable = false)
    private PositionEnum position;
//o	Note: The players table has relations with the towns, teams and stats tables.

    @ManyToOne
    @JoinColumn(name="stat_id", referencedColumnName = "id")
    private Stat stat;

    @ManyToOne
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name="town_id", referencedColumnName = "id")
    private Town town;

    public Player() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
