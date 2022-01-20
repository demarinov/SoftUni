package com.example.football.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsXmlDto implements Serializable {

    @XmlElement(name="stat")
    private List<StatXmlDto> statXmlDtos;

    public StatsXmlDto() {
    }

    public List<StatXmlDto> getStatXmlDtos() {
        return statXmlDtos;
    }

    public void setStatXmlDtos(List<StatXmlDto> statXmlDtos) {
        this.statXmlDtos = statXmlDtos;
    }
}
