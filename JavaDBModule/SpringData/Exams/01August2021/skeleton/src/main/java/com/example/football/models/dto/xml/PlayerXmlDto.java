package com.example.football.models.dto.xml;

import com.example.football.models.entity.PositionEnum;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerXmlDto implements Serializable {

    //•	first name – accepts char sequences as values where their character length value higher than 2.
    @XmlElement(name="first-name")
    @NotNull
    @Size(min=3)
    private String firstName;
    //•	last name – accepts char sequences as values where their character length value higher than 2.
    @XmlElement(name="last-name")
    @NotNull
    @Size(min=3)
    private String lastName;
    //•	email – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique
// in the database.
    @XmlElement(name="email")
    @NotNull
    @Email
    private String email;
//•	birth date – a date in the "dd/MM/yyyy" format.

    @XmlElement(name="birth-date")
    @NotNull
    private String birthDate;
    //•	position – one of the following – ATT, MID, DEF.
    @Enumerated
    @XmlElement(name="position")
    @NotNull
    private PositionEnum position;
//o	Note: The players table has relations with the towns, teams and stats tables.

    @XmlElement(name="stat")
    private StatIdXmlDto statIdXmlDto;

    @XmlElement(name="team")
    private TeamXmlDto teamXmlDto;

    @XmlElement(name="town")
    private TownXmlDto townXmlDto;

    public PlayerXmlDto() {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public StatIdXmlDto getStatIdXmlDto() {
        return statIdXmlDto;
    }

    public void setStatIdXmlDto(StatIdXmlDto statIdXmlDto) {
        this.statIdXmlDto = statIdXmlDto;
    }

    public TeamXmlDto getTeamXmlDto() {
        return teamXmlDto;
    }

    public void setTeamXmlDto(TeamXmlDto teamXmlDto) {
        this.teamXmlDto = teamXmlDto;
    }

    public TownXmlDto getTownXmlDto() {
        return townXmlDto;
    }

    public void setTownXmlDto(TownXmlDto townXmlDto) {
        this.townXmlDto = townXmlDto;
    }
}
