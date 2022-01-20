package com.nlt.app.controllers;

import com.nlt.app.services.CompanyService;
import com.nlt.app.services.EmployeeService;
import com.nlt.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController extends BaseController{

    private CompanyService companyService;
    private ProjectService projectService;
    private EmployeeService employeeService;

    @Autowired
    public HomeController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public ModelAndView home() {

        boolean areImported = false;

        if (companyService.areImported() && employeeService.areImported()
                && projectService.areImported()) {
            areImported = true;
        }

        return super.view("home","areImported",areImported);
    }

    @GetMapping("/logout")
    public ModelAndView logout() {

        return super.redirect("/");
    }
}
