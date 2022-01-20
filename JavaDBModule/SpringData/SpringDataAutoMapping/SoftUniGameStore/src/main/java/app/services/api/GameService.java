package app.services.api;

import app.model.entities.Game;

import java.util.List;

public interface GameService {

    List<Game> findGameByTitle(String title);
}
