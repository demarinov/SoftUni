package com.nlt.app.services.impl;

import com.nlt.app.model.dto.xml.EmployeesXmlDto;
import com.nlt.app.model.entities.Employee;
import com.nlt.app.model.entities.Project;
import com.nlt.app.repository.EmployeeRepository;
import com.nlt.app.repository.ProjectRepository;
import com.nlt.app.services.EmployeeService;
import com.nlt.app.services.ProjectService;
import com.nlt.app.utils.ValidationUtils;
import com.nlt.app.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_PATH = "src/main/resources/files/xmls/employees.xml";
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private ValidationUtils validationUtils;
    private XmlParser xmlParser;
    private ProjectService projectService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository projectRepository, ModelMapper modelMapper, ValidationUtils validationUtils,
                               XmlParser xmlParser, ProjectService projectService) {
        this.employeeRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.xmlParser = xmlParser;
        this.projectService = projectService;
    }

    @Override
    public boolean areImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readXmlContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(EMPLOYEE_PATH)));
    }

    @Override
    public String importData() throws JAXBException, FileNotFoundException {
        EmployeesXmlDto employeesXmlDto = xmlParser.fromFile(EMPLOYEE_PATH, EmployeesXmlDto.class);

        List<Employee> employees = employeesXmlDto.getEmployeeXmlDtos()
                .stream()
                .filter(employeeXmlDto -> validationUtils.isValid(employeesXmlDto))
                .map(employeeXmlDto -> {
                    Employee employee = modelMapper.map(employeeXmlDto, Employee.class);

                    Project employeeProject = getProjectByName(employeeXmlDto.
                            getProjectXmlDto().getName());

                    employee.getProject().setId(employeeProject.getId());

                    return employee;
                })
                .collect(Collectors.toList());

        employeeRepository.saveAll(employees);

        return "Succes";
    }

    @Override
    public Project getProjectByName(String name) {
        return projectService.getProjectByName(name).get(0);
    }

    @Override
    public List<Employee> getEmployeesByAgeGreaterThan() {
        return employeeRepository.getEmployeesByAgeGreaterThan(25);
    }


}
