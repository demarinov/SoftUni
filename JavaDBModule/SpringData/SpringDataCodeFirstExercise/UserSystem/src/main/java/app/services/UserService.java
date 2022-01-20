package app.services;

import app.models.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> findUserByUserName(String userName);

    void persist(User user);

    List<User> findUsersByEmailEnding(String end);

    List<User> findUsersNotLoggedAfterDate(Date dateTime);

    void delete(User user);

    void deactivateUsersBeforeDate(Date date);

    void deleteUsers();
}
