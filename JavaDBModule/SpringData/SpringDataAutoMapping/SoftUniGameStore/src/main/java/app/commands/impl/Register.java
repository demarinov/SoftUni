package app.commands.impl;

import app.commands.Command;
import app.commands.Executable;
import app.services.api.GameService;
import app.services.api.UserService;

public class Register extends Command {
    public Register(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String ... args) {

        return null;
    }
}
