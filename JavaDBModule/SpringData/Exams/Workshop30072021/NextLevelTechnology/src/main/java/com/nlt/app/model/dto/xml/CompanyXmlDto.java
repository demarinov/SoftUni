package com.nlt.app.model.dto.xml;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name="company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyXmlDto implements Serializable {

    @XmlAttribute(name="name")
    @NotBlank
    private String name;

    public CompanyXmlDto() {
    }

    public CompanyXmlDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
