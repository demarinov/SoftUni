package com.example.football.models.dto.xml;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatXmlDto implements Serializable {

    @XmlElement(name="shooting")
    @NotNull
    @Min(value=1)
    private Float shooting;
    //•	passing – a floating point number. The value must be positive (larger than 0).
    @XmlElement(name="passing")
    @NotNull
    @Min(value = 1)
    private Float passing;
    //•	endurance – a floating point number. The value must be positive (larger than 0).
    @XmlElement(name="endurance")
    @NotNull
    @Min(value = 1)
    private Float endurance;

    public StatXmlDto() {
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
