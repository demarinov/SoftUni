package app.services;

import app.entites.User;

import java.util.List;

public interface UserService {

    List<User> findAllById(Long id);

    void saveAll(User[] users);
}
