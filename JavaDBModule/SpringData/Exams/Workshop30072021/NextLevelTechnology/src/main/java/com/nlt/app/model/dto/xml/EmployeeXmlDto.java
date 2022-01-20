package com.nlt.app.model.dto.xml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXmlDto implements Serializable {

    //<employee>
    //        <first-name>Stamat</first-name>
    //        <last-name>Petrov</last-name>
    //        <age>45</age>
    //        <project>
    //            <name>HealthCare</name>
    //            <description>Pharmacy web app</description>
    //            <start-date>2017-07-12</start-date>
    //            <is-finished>true</is-finished>
    //            <payment>10000.00</payment>
    //            <company name="Phoenix"/>
    //        </project>
    //    </employee>

    @XmlElement(name="first-name")
    @NotBlank
    private String firstName;
    @XmlElement(name="last-name")
    @NotBlank
    private String lastName;
    @XmlElement(name="age")
    @NotNull
    private Integer age;
    @XmlElement(name="project")
    @NotNull
    private ProjectXmlDto projectXmlDto;

    public EmployeeXmlDto() {
    }

    public EmployeeXmlDto(String firstName, String lastName, Integer age, ProjectXmlDto projectXmlDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.projectXmlDto = projectXmlDto;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProjectXmlDto getProjectXmlDto() {
        return projectXmlDto;
    }

    public void setProjectXmlDto(ProjectXmlDto projectXmlDto) {
        this.projectXmlDto = projectXmlDto;
    }
}
