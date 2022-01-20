package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="towns")
public class Town extends BaseEntity {

    // •	id – accepts integer values, a primary identification field,
    // an auto incremented field.
    //•	name – accepts char sequences as values where their character
    @Column(name="name", nullable = false, unique = true)
    @Size(min=2)
    private String name;
    // length value higher than or equal to 2. The values are unique in the database.
    //•	population – accepts number values (must be a positive number), 0 as a value is exclusive.
    @Column(name="population", nullable = false)
    @Min(value=1)
    private Integer population;
    //•	travel guide – a long and detailed description of all known places with a
    // character length value higher than or equal to 10.
    @Column(name="travel_guide", nullable = false, columnDefinition = "text")
    @Size(min=10)
    private String travelGuide;

    public Town() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
