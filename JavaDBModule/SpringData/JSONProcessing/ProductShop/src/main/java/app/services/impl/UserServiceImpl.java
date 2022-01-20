package app.services.impl;

import app.entites.User;
import app.repositories.UserRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllById(Long id) {
        return userRepository.findAllById(id);
    }

    @Override
    public void saveAll(User[] users) {
        userRepository.saveAll(Arrays.asList(users));
    }
}
