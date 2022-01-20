package app.component;

import app.models.Account;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import app.services.AccountService;
import app.services.UserService;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("petko");
        user.setAge(19);

        Account account = new Account();
        account.setBalance(new BigDecimal("25000"));
        account.setUser(user);

        user.setAccounts(new HashSet() {{add(account);}});
        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal("20000"), account.getId());
        accountService.transferMoney(new BigDecimal("20000"), account.getId());
    }
}
