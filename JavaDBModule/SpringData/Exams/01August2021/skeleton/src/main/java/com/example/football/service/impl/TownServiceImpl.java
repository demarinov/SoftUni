package com.example.football.service.impl;

import com.example.football.models.dto.json.TownJsonDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtils;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private Gson gson;

    private static final String TOWN_PATH = "src/main/resources/files/json/towns.json";

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper,
                           ValidationUtils validationUtils, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(TOWN_PATH)));
    }

    @Override
    public String importTowns() throws IOException {

        TownJsonDto[] townJsonDtos = gson.fromJson(readTownsFileContent(), TownJsonDto[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(townJsonDtos)
                .filter(townJsonDto -> {

                    boolean isValid = validationUtils.isValid(townJsonDto)
                            && !entityTownExists(townJsonDto.getName());

                    sb.append(isValid ? String.format("Successfully imported Town %s - %d%n",
                            townJsonDto.getName(),townJsonDto.getPopulation()) : "Invalid Town\n");

                    return isValid;

                })
                .map(townJsonDto -> {
                    Town town = modelMapper.map(townJsonDto, Town.class);

                    return town;
                })
                .forEach(town -> townRepository.save(town));

        return sb.toString();
    }

    @Override
    public Town findTownByName(String townName) {
        return townRepository.findTownByName(townName).get(0);
    }

    private boolean entityTownExists(String name) {

        return townRepository.existsByName(name);
    }
}
