package com.nlt.app.services.impl;

import com.nlt.app.model.dto.xml.ProjectXmlDto;
import com.nlt.app.model.dto.xml.ProjectsXmlDto;
import com.nlt.app.model.entities.Company;
import com.nlt.app.model.entities.Project;
import com.nlt.app.repository.ProjectRepository;
import com.nlt.app.services.CompanyService;
import com.nlt.app.services.ProjectService;
import com.nlt.app.utils.ValidationUtils;
import com.nlt.app.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;
    private ValidationUtils validationUtils;
    private XmlParser xmlParser;
    private CompanyService companyService;

    private static final String PROJECT_PATH = "src/main/resources/files/xmls/projects.xml";

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper,
                              ValidationUtils validationUtils, XmlParser xmlParser
                              ,CompanyService companyService) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.xmlParser = xmlParser;
        this.companyService = companyService;
    }

    @Override
    public boolean areImported() {
        return projectRepository.count() > 0;
    }

    @Override
    public String readXmlContent() throws IOException {

        return new String(Files.readAllBytes(Paths.get(PROJECT_PATH)));
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return projectRepository.getProjectByName(name);
    }

    @Override
    public List<Project> getProjectsByIsFinished() {
        return projectRepository.getProjectsByFinished(true);
    }


    @Override
    public String importProjects() throws JAXBException, FileNotFoundException {

        ProjectsXmlDto projectsXmlDto = xmlParser.fromFile(PROJECT_PATH, ProjectsXmlDto.class);

        List<Project> projectList = projectsXmlDto.getProjectXmlDtoList()
                .stream()
                .filter(projectXmlDto -> {
                    boolean isValid = validationUtils.isValid(projectsXmlDto);

                    return isValid;
                })
                .map(projectXmlDto -> {
                    Project project = modelMapper.map(projectXmlDto, Project.class);

                    Company projectCompany = project.getCompany();

                    projectCompany.setId(companyService.getCompanyByName(projectCompany.getName())
                            .getId());

                    return project;
                })
                .collect(Collectors.toList());

        projectRepository.saveAll(projectList);

        return "Success";
    }


}
