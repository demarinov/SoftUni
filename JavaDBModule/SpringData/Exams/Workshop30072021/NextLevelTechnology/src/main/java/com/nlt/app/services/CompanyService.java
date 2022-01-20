package com.nlt.app.services;

import com.nlt.app.model.entities.BaseEntity;
import com.nlt.app.model.entities.Company;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CompanyService {

    boolean areImported();

    String readXmlContent() throws IOException;

    String importData() throws JAXBException, FileNotFoundException;

    Company getCompanyByName(String name);
}
