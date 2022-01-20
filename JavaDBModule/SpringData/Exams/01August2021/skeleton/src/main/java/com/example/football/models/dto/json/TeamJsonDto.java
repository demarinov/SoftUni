package com.example.football.models.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeamJsonDto {


    @Expose
    @NotNull
    @Size(min=3)
    private String name;
    // length value higher than or equal to 3. The values are unique in the database.
    //•	stadium name – accepts char sequences as values where their character
    @Expose
    @NotNull
    @Size(min=3)
    private String stadiumName;
    // length value higher than or equal to 3.
    //•	fan base – accepts number values that are more than or equal to 1000.
    @Expose
    @NotNull
    @Min(value = 1000)
    private Integer fanBase;
    //•	history – a long and detailed description of team's history
    @Expose
    @NotNull
    @Size(min=10)
    private String history;

    @Expose
    @NotNull
    private String townName;

    public TeamJsonDto() {
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

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
