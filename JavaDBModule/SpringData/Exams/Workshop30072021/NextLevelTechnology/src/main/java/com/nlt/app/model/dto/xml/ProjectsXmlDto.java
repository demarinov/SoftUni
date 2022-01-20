package com.nlt.app.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectsXmlDto implements Serializable {

    // <project>
    //        <name>HealthCare</name>
    //        <description>Pharmacy web app</description>
    //        <start-date>2017-07-12</start-date>
    //        <is-finished>true</is-finished>
    //        <payment>10000.00</payment>
    //        <company name="Phoenix"/>
    //    </project>

    @XmlElement(name="project")
    private List<ProjectXmlDto> projectXmlDtoList;

    public ProjectsXmlDto() {
    }

    public ProjectsXmlDto(List<ProjectXmlDto> projectXmlDtoList) {
        this.projectXmlDtoList = projectXmlDtoList;
    }

    public List<ProjectXmlDto> getProjectXmlDtoList() {
        return projectXmlDtoList;
    }

    public void setProjectXmlDtoList(List<ProjectXmlDto> projectXmlDtoList) {
        this.projectXmlDtoList = projectXmlDtoList;
    }
}
