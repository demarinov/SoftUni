package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name="stats")
public class Stat extends BaseEntity{

    // •	id – accepts integer values, a primary identification field, an auto incremented field.
    //•	shooting – a floating point number. The value must be positive (larger than 0).
    @Column(name="shooting", nullable = false)
    @Min(value=1)
    private Float shooting;
    //•	passing – a floating point number. The value must be positive (larger than 0).
    @Column(name="passing", nullable = false)
    @Min(value = 1)
    private Float passing;
    //•	endurance – a floating point number. The value must be positive (larger than 0).
    @Column(name="endurance", nullable = false)
    @Min(value = 1)
    private Float endurance;

    public Stat() {

    }

    public Float getShooting() {
        return shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }

    public Float getPassing() {
        return passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }

    public Float getEndurance() {
        return endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
