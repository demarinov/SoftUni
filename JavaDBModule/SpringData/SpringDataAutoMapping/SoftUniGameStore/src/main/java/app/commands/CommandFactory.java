package app.commands;

import app.commands.impl.*;
import app.constants.CommandConstants;
import app.services.api.GameService;
import app.services.api.UserService;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private final UserService userService;
    private final GameService gameService;

    private CommandFactory(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public Executable getCommand(String command) {
        switch (command) {
            case CommandConstants.REGISTER_USER:
                return new Register(this.userService, this.gameService);
            case CommandConstants.LOGIN_USER:
                return new Login(this.userService, this.gameService);
            case CommandConstants.LOGOUT_USER:
                return new Logout(this.userService, this.gameService);
            case CommandConstants.ADD_GAME:
                return new AddGame(this.userService, this.gameService);
            case CommandConstants.EDIT_GAME:
                return new EditGame(this.userService, this.gameService);
            case CommandConstants.DELETE_GAME:
                return new DeleteGame(this.userService, this.gameService);
            case CommandConstants.ALL_GAME:
                return new AllGameTilesAndPrice(this.userService, this.gameService);
            case CommandConstants.DETAIL_GAME:
                return new DetailGameView(this.userService, this.gameService);
            case CommandConstants.OWNED_GAME:
                return new OwnedGames(this.userService, this.gameService);
            case CommandConstants.ADD_ITEM:
                return new AddItem(this.userService, this.gameService);
            case CommandConstants.REMOVE_ITEM:
                return new RemoveItem(this.userService, this.gameService);
            case CommandConstants.BUY_ITEM:
                return new BuyItem(this.userService, this.gameService);
            default:
                return null;
        }
    }
}
