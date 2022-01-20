package com.example.football.service.impl;

import com.example.football.models.dto.json.TeamJsonDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {


    private TeamRepository teamRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private Gson gson;

    private TownService townService;

    private static final String TEAM_PATH = "src/main/resources/files/json/teams.json";

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper,
                           ValidationUtils validationUtils, Gson gson,
                           TownService townService) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(TEAM_PATH)));
    }

    @Override
    public String importTeams() throws IOException {

        TeamJsonDto[] teamJsonDtos = gson.fromJson(readTeamsFileContent(), TeamJsonDto[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(teamJsonDtos)
                .filter(teamJsonDto -> {
                    boolean isValid = validationUtils.isValid(teamJsonDto)
                            && !entityTeamExists(teamJsonDto.getName());

                    sb.append(isValid ? String.format("Successfully imported Team %s - %d%n",
                            teamJsonDto.getName(),teamJsonDto.getFanBase()) : "Invalid Team\n");

                    return isValid;

                })
                .map(teamJsonDto -> {

                    Team team = modelMapper.map(teamJsonDto, Team.class);

                    Town town = townService.findTownByName(teamJsonDto.getTownName());

                    team.setTown(town);

                    return team;
                })
                .forEach(team -> teamRepository.save(team));


        return sb.toString();
    }

    @Override
    public Team findTeamByName(String name) {
        return teamRepository.findTeamByName(name).get(0);
    }

    private boolean entityTeamExists(String name) {
        return teamRepository.existsByName(name);
    }
}
