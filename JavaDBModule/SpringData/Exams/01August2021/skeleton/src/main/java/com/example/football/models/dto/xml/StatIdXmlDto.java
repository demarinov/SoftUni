package com.example.football.models.dto.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatIdXmlDto implements Serializable {

    @XmlElement(name="id")
    @NotNull
    private Long id;

    public StatIdXmlDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
