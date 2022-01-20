package app.commands.impl;

import app.commands.Executable;
import app.services.api.GameService;
import app.services.api.UserService;

public class RemoveItem implements Executable {

    public RemoveItem(UserService userService, GameService gameService) {
    }

    @Override
    public String execute(String... args) {
        return null;
    }
}
