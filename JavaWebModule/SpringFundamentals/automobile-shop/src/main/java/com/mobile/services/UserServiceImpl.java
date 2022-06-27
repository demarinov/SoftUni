package com.mobile.services;

import com.mobile.components.CurrentUser;
import com.mobile.models.dtos.UserDto;
import com.mobile.models.dtos.UserLoginDto;
import com.mobile.models.entities.UserEntity;
import com.mobile.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.getAllBy();
    }

    @Override
    public Boolean login(UserLoginDto userDto, HttpSession session) {
        UserEntity realUser = getUserByUsername(userDto.getUserName());

        if (realUser == null) {
            log.debug("User with {} not found", userDto.getUserName());
            return false;
        }

        String rawPassword = userDto.getPassword();
        String encodedPassword = realUser.getPassword();

        Boolean isPasswordValid = passwordEncoder.matches(rawPassword, encodedPassword);

        if (Boolean.TRUE.equals(isPasswordValid)) {
            session.setAttribute("user", mapper.map(realUser, UserDto.class));
            session.setAttribute("userRole", realUser.getUserRoles().get(0).getRole().name());

            login(realUser);
        } else {
            logout();
        }

        return isPasswordValid;
    }

    @Override
    public Boolean register(UserDto userDto, String[] userRoles, HttpSession session) {

        userDto.normalizeUserRole(userRoles);

        boolean userAdded = addUser(userDto);

        session.setAttribute("user", userDto);
        currentUser.setLoggedIn(true);
        currentUser.setName(userDto.getFirstName() +" "+ userDto.getLastName());

        return userAdded;
    }

    private void login(UserEntity user) {
        currentUser.setLoggedIn(true);
        currentUser.setName(user.getFirstName() +" "+ user.getLastName());
        currentUser.setUsername(user.getUserName());
        currentUser.setId(user.getId());
    }

    public void logout() {
        currentUser.clear();
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.getByUserName(username);
    }

    private boolean addUser(UserDto userDto) {

        if (userDto != null) {
            UserEntity user = mapper.map(userDto, UserEntity.class);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setIsActive(true);
            user.setCreated(LocalDateTime.now());
            user.setModified(LocalDateTime.now());
            userRepository.save(user);

            return true;
        }

        return false;
    }

    public boolean isUserLoggedIn() {
        return currentUser.isLoggedIn();
    }
}
