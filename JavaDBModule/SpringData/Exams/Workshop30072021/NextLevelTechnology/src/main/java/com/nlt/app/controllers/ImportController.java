package com.nlt.app.controllers;

import com.nlt.app.services.CompanyService;
import com.nlt.app.services.EmployeeService;
import com.nlt.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController{

    private CompanyService companyService;
    private ProjectService projectService;
    private EmployeeService employeeService;

    @Autowired
    public ImportController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping({"","/xml"})
    public ModelAndView importHome() {

        boolean[] areImported = new boolean[]{
                this.companyService.areImported(),
                this.projectService.areImported(),
                this.employeeService.areImported()
        };

        if (areImported[0] == true && areImported[1] == true && areImported[2] == true) {

            return super.redirect("/home");
        }

        return super.view("xml/import-xml", "areImported", areImported);
    }

    @GetMapping("/companies")
    public ModelAndView importCompanies() throws IOException {
        String companies = companyService.readXmlContent();
        return super.view("xml/import-companies", "companies",companies);
    }

    @GetMapping("/projects")
    public ModelAndView importProjects() throws IOException {
        String projects = projectService.readXmlContent();
        return super.view("xml/import-projects", "projects",projects);
    }

    @GetMapping("/employees")
    public ModelAndView importEmployees() throws IOException {
        String employees = employeeService.readXmlContent();
        return super.view("xml/import-employees", "employees",employees);
    }

    @PostMapping("/companies")
    public ModelAndView importCompaniesConfirm() throws JAXBException, FileNotFoundException {
        companyService.importData();
        return super.redirect("/import");
    }

    @PostMapping("/projects")
    public ModelAndView importProjectsConfirm() throws JAXBException, FileNotFoundException {
        projectService.importProjects();
        return super.redirect("/import");
    }

    @PostMapping("/employees")
    public ModelAndView importEmployeesConfirm() throws JAXBException, FileNotFoundException {
        employeeService.importData();
        return super.redirect("/import");
    }
}
