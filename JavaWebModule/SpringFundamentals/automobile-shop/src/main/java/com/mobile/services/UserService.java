package com.mobile.services;

import com.mobile.models.dtos.UserDto;
import com.mobile.models.dtos.UserLoginDto;
import com.mobile.models.entities.UserEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();
    Boolean login(UserLoginDto userDto, HttpSession session);
    Boolean register(UserDto userDto,String[] userRoles, HttpSession session);
    UserEntity getUserByUsername(String username);
    void logout();
    boolean isUserLoggedIn();
}
