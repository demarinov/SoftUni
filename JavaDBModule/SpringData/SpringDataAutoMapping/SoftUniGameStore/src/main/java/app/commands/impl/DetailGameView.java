package app.commands.impl;

import app.commands.Executable;
import app.services.api.GameService;
import app.services.api.UserService;

public class DetailGameView implements Executable {

    public DetailGameView(UserService userService, GameService gameService) {
    }

    @Override
    public String execute(String... args) {
        return null;
    }
}
