package com.example.football.models.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TownJsonDto {

    @Expose
    @NotNull
    @Size(min=2)
    private String name;

    @Expose
    @NotNull
    @Min(value=1)
    private Integer population;

    @Expose
    @NotNull
    @Size(min=10)
    private String travelGuide;

    public TownJsonDto() {
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
