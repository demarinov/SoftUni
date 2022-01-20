package com.nlt.app.services;

import com.nlt.app.model.entities.Company;
import com.nlt.app.model.entities.Employee;
import com.nlt.app.model.entities.Project;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    boolean areImported();

    String readXmlContent() throws IOException;

    String importData() throws JAXBException, FileNotFoundException;

    Project getProjectByName(String name);

    List<Employee> getEmployeesByAgeGreaterThan();
}
