package com.nlt.app.model.dto.xml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectXmlDto implements Serializable {

    @XmlElement(name="name")
    @NotBlank
    private String name;

    @XmlElement(name="description")
    @NotBlank
    private String description;

    @XmlElement(name="start-date")
    private String startDate;

    @XmlElement(name="is-finished")
    private String finished;

    @XmlElement(name="payment")
    @NotBlank
    private String payment;

    @XmlElement(name="company")
    @NotNull
    private CompanyXmlDto companyXmlDto;

    public ProjectXmlDto() {
    }

    public ProjectXmlDto(@NotBlank String name, @NotBlank String description,
                         String startDate, String finished, @NotBlank String payment,
                         @NotNull CompanyXmlDto companyXmlDto) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.finished = finished;
        this.payment = payment;
        this.companyXmlDto = companyXmlDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public CompanyXmlDto getCompanyXmlDto() {
        return companyXmlDto;
    }

    public void setCompanyXmlDto(CompanyXmlDto companyXmlDto) {
        this.companyXmlDto = companyXmlDto;
    }
}
