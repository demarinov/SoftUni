package com.nlt.app.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesXmlDto implements Serializable {

    @XmlElement(name="employee")
    List<EmployeeXmlDto> employeeXmlDtos;

    public EmployeesXmlDto() {
    }

    public EmployeesXmlDto(List<EmployeeXmlDto> employeeXmlDtos) {
        this.employeeXmlDtos = employeeXmlDtos;
    }

    public List<EmployeeXmlDto> getEmployeeXmlDtos() {
        return employeeXmlDtos;
    }

    public void setEmployeeXmlDtos(List<EmployeeXmlDto> employeeXmlDtos) {
        this.employeeXmlDtos = employeeXmlDtos;
    }
}
