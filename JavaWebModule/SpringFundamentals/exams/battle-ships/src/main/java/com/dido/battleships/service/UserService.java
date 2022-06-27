package com.dido.battleships.service;

import com.dido.battleships.component.CurrentUser;
import com.dido.battleships.model.dto.UserDto;
import com.dido.battleships.model.dto.UserLoginDto;
import com.dido.battleships.model.entity.UserEntity;
import com.dido.battleships.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;

    public boolean login(UserLoginDto userLoginDto) {

        UserEntity dbUser = userRepository.findByUsername(userLoginDto.getUsername()).orElse(null);

        if (dbUser != null) {

            if (dbUser.getPassword().equals(userLoginDto.getPassword())) {
                currentUser.setUsername(userLoginDto.getUsername());
                currentUser.setLoggedIn(true);
                currentUser.setId(dbUser.getId());
            } else {
                log.warn("Invalid username or password.");
            }
        }

        log.warn("Invalid username or password.");
        return false;
    }

    public boolean register(UserDto userDto) {

        if (userDto != null) {
            // add times and others ...

            UserEntity newDbUser = mapper.map(userDto, UserEntity.class);
            userRepository.save(newDbUser);
            currentUser.setUsername(userDto.getUsername());
            currentUser.setLoggedIn(true);
            currentUser.setId(newDbUser.getId());
            return true;
        }

        return false;
    }

    public boolean logout() {
        currentUser.clear();
        return true;
    }

    public boolean isLoggedIn() {
        return currentUser.isLoggedIn();
    }

    public UserEntity findByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserEntity findByUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findCurrentUser() {
        return userRepository.findByUsername(currentUser.getUsername()).orElse(null);
    }
}
