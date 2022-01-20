package com.example.football.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersXmlDto implements Serializable {

    @XmlElement(name="player")
    private List<PlayerXmlDto> playerXmlDtos;

    public PlayersXmlDto() {
    }

    public List<PlayerXmlDto> getPlayerXmlDtos() {
        return playerXmlDtos;
    }

    public void setPlayerXmlDtos(List<PlayerXmlDto> playerXmlDtos) {
        this.playerXmlDtos = playerXmlDtos;
    }
}
