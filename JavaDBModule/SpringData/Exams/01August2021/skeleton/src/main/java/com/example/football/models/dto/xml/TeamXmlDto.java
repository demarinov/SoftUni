package com.example.football.models.dto.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamXmlDto implements Serializable {

    @XmlElement(name="name")
    @NotNull
    private String name;

    public TeamXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
