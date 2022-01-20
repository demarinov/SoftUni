package com.nlt.app.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompaniesXmlDto implements Serializable {


    @XmlElement(name="company")
    private List<CompanyXmlDto> companyXmlDtoList;

    public CompaniesXmlDto() {
    }

    public CompaniesXmlDto(List<CompanyXmlDto> companyXmlDtoList) {
        this.companyXmlDtoList = companyXmlDtoList;
    }

    public List<CompanyXmlDto> getCompanyXmlDtoList() {
        return companyXmlDtoList;
    }

    public void setCompanyXmlDtoList(List<CompanyXmlDto> companyXmlDtoList) {
        this.companyXmlDtoList = companyXmlDtoList;
    }
}
