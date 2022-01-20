package com.example.football.service.impl;

import com.example.football.models.dto.xml.StatsXmlDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtils;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class StatServiceImpl implements StatService {

    private StatRepository statRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private XmlParser xmlParser;

    private static final String STAT_PATH = "src/main/resources/files/xml/stats.xml";

    @Autowired
    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper,
                           ValidationUtils validationUtils, XmlParser xmlParser) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(STAT_PATH)));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {

        StatsXmlDto statsXmlDto = xmlParser.fromFile(STAT_PATH, StatsXmlDto.class);

        StringBuilder sb = new StringBuilder();
        statsXmlDto.getStatXmlDtos().stream()
                .filter(statXmlDto -> {

                    boolean isValid = validationUtils.isValid(statXmlDto)
                            && !entityStatExists(statXmlDto.getShooting(),
                            statXmlDto.getEndurance(), statXmlDto.getPassing());

                    sb.append(isValid ? String.format("Successfully imported Stat %.2f - %.2f - %.2f%n",
                            statXmlDto.getShooting(),statXmlDto.getPassing(),
                            statXmlDto.getEndurance()) : "Invalid Stat\n");

                    return isValid;

                })
                .map(statXmlDto -> {
                    Stat stat = modelMapper.map(statXmlDto, Stat.class);

                    return stat;
                })
                .forEach(stat -> statRepository.save(stat));

        return sb.toString();
    }

    @Override
    public Stat findStatById(Long id) {
        return statRepository.findById(id).orElse(null);
    }

    private boolean entityStatExists(Float shooting, Float endurance, Float passing) {

        return statRepository.existsByShootingAndEnduranceAndPassing(shooting, endurance, passing);
    }
}
