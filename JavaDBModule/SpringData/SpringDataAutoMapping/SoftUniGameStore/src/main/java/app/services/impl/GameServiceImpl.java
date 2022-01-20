package app.services.impl;

import app.model.entities.Game;
import app.repositories.GameRepository;
import app.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findGameByTitle(String title) {
        return gameRepository.findGameByTitle(title);
    }
}
