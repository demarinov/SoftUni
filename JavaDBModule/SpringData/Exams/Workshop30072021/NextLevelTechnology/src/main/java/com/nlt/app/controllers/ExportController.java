package com.nlt.app.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.nlt.app.model.entities.Employee;
import com.nlt.app.model.entities.Project;
import com.nlt.app.services.EmployeeService;
import com.nlt.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {


    private ProjectService projectService;
    private EmployeeService employeeService;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/project-if-finished")
    public ModelAndView exportProjectsIfFinished() {
        List<Project> projectsIfFinished = projectService.getProjectsByIsFinished();
        StringBuilder sb = new StringBuilder();
        projectsIfFinished.stream().forEach(p -> sb.append(p));
        return super.view("/export/export-project-if-finished",
                "projectsIfFinished",sb.toString());
    }

    @GetMapping("/employees-above")
    public ModelAndView exportEmployeesWithAge() {
        List<Employee> employees = employeeService.getEmployeesByAgeGreaterThan();
        StringBuilder sb = new StringBuilder();
        employees.stream().forEach(p -> sb.append(p));
        return super.view("/export/export-employees-with-age","employeesAbove",
                sb.toString());
    }
}
