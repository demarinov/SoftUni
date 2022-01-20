package com.example.football.service.impl;

import com.example.football.models.dto.xml.PlayersXmlDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
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
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private XmlParser xmlParser;

    private TeamService teamService;

    private TownService townService;

    private StatService statService;

    private static final String PLAYER_PATH = "src/main/resources/files/xml/players.xml";

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper,
                             ValidationUtils validationUtils, XmlParser xmlParser,
                             TeamService teamService, TownService townService,
                             StatService statService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.xmlParser = xmlParser;
        this.teamService = teamService;
        this.townService = townService;
        this.statService = statService;

    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(PLAYER_PATH)));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {

        PlayersXmlDto playersXmlDto = xmlParser.fromFile(PLAYER_PATH, PlayersXmlDto.class);

        StringBuilder sb = new StringBuilder();

        playersXmlDto.getPlayerXmlDtos().stream()
                .filter(playerXmlDto -> {
                    boolean isValid = validationUtils.isValid(playerXmlDto)
                            && !entityPlayerExists(playerXmlDto.getEmail());

                    sb.append(isValid ? String.format("Successfully imported Player %s %s - %s%n",
                            playerXmlDto.getFirstName(), playerXmlDto.getLastName(),
                            playerXmlDto.getPosition()) : "Invalid Player\n");

                    return isValid;
                })
                .map(playerXmlDto -> {

                    Player player = modelMapper.map(playerXmlDto, Player.class);

                    Team team = teamService.findTeamByName(playerXmlDto.getTeamXmlDto().getName());
                    player.setTeam(team);

                    Town town = townService.findTownByName(playerXmlDto.getTownXmlDto().getName());
                    player.setTown(town);

                    Stat stat = statService.findStatById(playerXmlDto.getStatIdXmlDto().getId());
                    player.setStat(stat);

                    return player;

                })
                .forEach(player -> playerRepository.save(player));

        return sb.toString();
    }

    private boolean entityPlayerExists(String email) {

        return playerRepository.existsByEmail(email);
    }

    @Override
    public String exportBestPlayers() {

        StringBuilder sb = new StringBuilder();

        // "Player - {firstName} {lastName}
        //	Position - {position name}
        //Team - {team name}
        //	Stadium - {stadium name}

        List<Player> bestPlayers = playerRepository.exportBestPlayers();

        for (Player player : bestPlayers) {
            sb.append(String.format("Player - %s %s%n", player.getFirstName(),
                    player.getLastName()));

            sb.append(String.format("\tPosition - %s%n", player.getPosition()));
            sb.append(String.format("\tTeam - %s%n", player.getTeam().getName()));
            sb.append(String.format("\tStadium - %s%n", player.getTeam().getStadiumName()));
        }
        return sb.toString();
    }
}
