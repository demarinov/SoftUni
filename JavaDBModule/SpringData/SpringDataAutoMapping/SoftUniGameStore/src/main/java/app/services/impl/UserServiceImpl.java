package app.services.impl;

import app.model.entities.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUserByFullName(String fullName) {
        return userRepository.findUserByFullName(fullName);
    }
}
