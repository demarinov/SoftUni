package com.dido.pathfinder.service;

import com.dido.pathfinder.components.CurrentUser;
import com.dido.pathfinder.model.dto.UserDto;
import com.dido.pathfinder.model.dto.UserLoginDto;
import com.dido.pathfinder.model.entity.UserEntity;
import com.dido.pathfinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;

    @Override
    public boolean login(UserLoginDto userLoginDto) {

        UserEntity dbUser = userRepository.findByUsername(userLoginDto.getUsername()).orElse(null);

        if (dbUser != null) {

            if (dbUser.getPassword().equals(userLoginDto.getPassword())) {
                currentUser.setUsername(userLoginDto.getUsername());
                currentUser.setLoggedIn(true);
                currentUser.setId(dbUser.getId());
            } else {
                log.debug("Invalid username or password.");
            }
        }

        return false;
    }

    @Override
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

    @Override
    public boolean logout() {
        currentUser.clear();
        return true;
    }


}
