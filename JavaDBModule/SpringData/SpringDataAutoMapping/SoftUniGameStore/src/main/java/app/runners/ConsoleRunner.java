package app.runners;

import app.commands.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CommandFactory commandFactory;

    @Autowired
    public ConsoleRunner(final CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
