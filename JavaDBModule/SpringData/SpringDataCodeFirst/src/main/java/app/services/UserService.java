package app.services;

import app.models.User;
import org.springframework.stereotype.Service;

public interface UserService {

    void registerUser(User user);
}
