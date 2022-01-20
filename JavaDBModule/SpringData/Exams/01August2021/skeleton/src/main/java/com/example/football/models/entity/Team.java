package com.example.football.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="teams")
public class Team extends BaseEntity{

    // •	id – accepts integer values, a primary identification field, an auto incremented field.
    //•	name – accepts char sequences as values where their character
    @Column(name="name", nullable = false, unique = true)
    @Size(min=3)
    private String name;
    // length value higher than or equal to 3. The values are unique in the database.
    //•	stadium name – accepts char sequences as values where their character
    @Column(name="stadium_name", nullable = false)
    @Size(min=3)
    private String stadiumName;
    // length value higher than or equal to 3.
    //•	fan base – accepts number values that are more than or equal to 1000.
    @Column(name="fan_base", nullable = false)
    @Min(value = 1000)
    private Integer fanBase;
    //•	history – a long and detailed description of team's history
    @Column(name="history", nullable = false, columnDefinition = "text")
    @Size(min=10)
    private String history;
    // with a character length value higher than or equal to 10.
    //o	Note: The teams table has relation with the towns table.

    @OneToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getFanBase() {
        return fanBase;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
