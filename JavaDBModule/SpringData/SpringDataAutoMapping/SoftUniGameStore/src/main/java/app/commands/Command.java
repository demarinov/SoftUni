package app.commands;

import app.services.api.GameService;
import app.services.api.UserService;

public abstract class Command implements Executable{

    private final UserService userService;
    private final GameService gameService;

    protected Command(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected GameService getGameService() {
        return gameService;
    }
}
