package app.services.api;

import app.model.entities.User;

import java.util.List;

public interface UserService {

    List<User> findUserByFullName(String fullName);
}
