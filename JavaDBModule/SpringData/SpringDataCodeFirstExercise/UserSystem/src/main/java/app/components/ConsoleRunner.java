package app.components;

import app.models.Album;
import app.models.Picture;
import app.models.Town;
import app.models.User;
import app.services.AlbumService;
import app.services.PictureService;
import app.services.TownService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner{

    private UserService userService;
    private AlbumService albumService;
    private PictureService pictureService;
    private TownService townService;


    @Autowired
    public ConsoleRunner(UserService userService, AlbumService albumService,
                         PictureService pictureService, TownService townService) {
        this.userService = userService;
        this.albumService = albumService;
        this.pictureService = pictureService;
        this.townService = townService;
    }

    @Override
    public void run(String... args) throws Exception {

        Picture picture = new Picture();
        picture.setTitle("Shot in the bath");

        pictureService.persist(picture);

        Town town = new Town();
        town.setName("Old town");
        town.setCountry("Lovradia");

        townService.persist(town);

        Town townTwo = new Town();
        town.setName("New town");
        town.setCountry("Lovradia");

        townService.persist(townTwo);


        User user = new User();
        user.setFirstName("Allen");
        user.setLastName("Delon");
        user.setAge(20);
        user.setEmail("user@mail.bg");
        user.setUserName("beto23");
        user.setPassword("Be0rto");
        user.setTownBorn(town);
        user.setTownLiving(townTwo);
        user.setLastTimeLoggedIn(new Date());


        userService.persist(user);

        User userTwo = new User();
        userTwo.setFirstName("Bob");
        userTwo.setLastName("Beron");
        userTwo.setAge(21);
        userTwo.setEmail("bobbe@mail.bg");
        userTwo.setUserName("beto23");
        userTwo.setPassword("Be0rto");
        userTwo.setFriends(new HashSet(){{add(user);}});
        userTwo.setTownLiving(town);
        userTwo.setTownBorn(townTwo);

        userService.persist(userTwo);

        Album album = new Album();
        album.setName("Born again");
        album.setPublic(true);
        album.setUser(user);
        album.setPictures(new HashSet(){{add(picture);}});

        albumService.persist(album);

        Scanner sc = new Scanner(System.in);
        printUsersByEmailEnding(sc.nextLine().trim());

        printUsersLastTimeLoggedBeforeDate(new Date());

    }

    private void printUsersLastTimeLoggedBeforeDate(Date date) {

        List<User> usersNotLogged = userService.findUsersNotLoggedAfterDate(date);

        System.out.println(usersNotLogged.size());

        userService.deactivateUsersBeforeDate(date);

        userService.deleteUsers();
    }

    private void printUsersByEmailEnding(String end) {

        List<User> users = userService.findUsersByEmailEnding(end);

        users.forEach(s -> System.out.printf("%s %s%n", s.getUserName(), s.getEmail()));

        if (users.isEmpty()) {
            System.out.println("No users found with email domain "+end);
        }
    }
}
