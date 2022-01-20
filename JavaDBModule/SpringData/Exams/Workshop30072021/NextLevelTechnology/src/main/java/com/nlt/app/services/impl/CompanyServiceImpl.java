package com.nlt.app.services.impl;

import com.nlt.app.model.dto.xml.CompaniesXmlDto;
import com.nlt.app.model.dto.xml.CompanyXmlDto;
import com.nlt.app.model.entities.Company;
import com.nlt.app.repository.CompanyRepository;
import com.nlt.app.services.CompanyService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;
    private ValidationUtils validationUtils;
    private XmlParser xmlParser;

    private static final String COMPANY_PATH = "src/main/resources/files/xmls/companies.xml";

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper,
                              ValidationUtils validationUtils, XmlParser xmlParser) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return companyRepository.count() > 0;
    }

    @Override
    public String readXmlContent() throws IOException {

        return new String(Files.readAllBytes(Paths.get(COMPANY_PATH)));
    }

    @Override
    public String importData() throws JAXBException, FileNotFoundException {

        CompaniesXmlDto companyXmlDtos = xmlParser.fromFile(COMPANY_PATH, CompaniesXmlDto.class);

        List<Company> companies = companyXmlDtos.getCompanyXmlDtoList()
                .stream().filter(companyXmlDto -> validationUtils.isValid(companyXmlDto))
                .map(companyXmlDto -> {
                    Company company = modelMapper.map(companyXmlDto, Company.class);

                    return company;
                })
                .collect(Collectors.toList());

        companyRepository.saveAll(companies);

        return "Success";
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyRepository.getCompanyByName(name).get(0);
    }

}
