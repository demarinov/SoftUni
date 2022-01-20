package app.services;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if (userRepository.getByUserName(user.getUserName()) == null) {
            userRepository.save(user);
        }
    }
}
