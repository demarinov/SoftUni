package com.nlt.app.services;

import com.nlt.app.model.entities.Project;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProjectService {

    boolean areImported();

    String importProjects() throws JAXBException, FileNotFoundException;

    String readXmlContent() throws IOException;

    List<Project> getProjectByName(String name);

    List<Project> getProjectsByIsFinished();
}
