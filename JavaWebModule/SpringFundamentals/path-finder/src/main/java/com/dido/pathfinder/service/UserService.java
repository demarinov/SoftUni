package com.dido.pathfinder.service;

import com.dido.pathfinder.model.dto.UserDto;
import com.dido.pathfinder.model.dto.UserLoginDto;

public interface UserService {

    boolean login(UserLoginDto userLoginDto);
    boolean register(UserDto userDto);

    boolean logout();
}
