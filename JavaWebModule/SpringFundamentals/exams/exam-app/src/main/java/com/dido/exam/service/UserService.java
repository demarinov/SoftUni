package com.dido.exam.service;

import com.dido.exam.component.CurrentUser;
import com.dido.exam.model.dto.UserDto;
import com.dido.exam.model.dto.UserLoginDto;
import com.dido.exam.model.entity.UserEntity;
import com.dido.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public boolean login(UserLoginDto userLoginDto) {

        UserEntity dbUser = userRepository.findByUsername(userLoginDto.getUsername()).orElse(null);

        if (dbUser != null) {

            if (passwordEncoder.matches(userLoginDto.getPassword(), dbUser.getPassword())) {
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

            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                return false;
            }

            UserEntity newDbUser = mapper.map(userDto, UserEntity.class);

            newDbUser.setPassword(passwordEncoder.encode(userDto.getPassword()));


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

    public boolean updateUser(UserEntity userEntity) {

        if (userEntity != null) {
            userRepository.save(userEntity);

            return true;
        }

        return false;
    }
}
