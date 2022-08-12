package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.UserRegisterDto;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.model.enums.RoleEnum;
import com.dido.holidaybay.repository.UserRepository;
import com.dido.holidaybay.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTests {

    // using autowired repo and not mock just for unit testing and exercise in this one...
    // ... after running the tests maybe it is a good idea to drop dbs and run the apps again

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;

    @Autowired
    private ModelMapper mapper;


    @BeforeEach()
    void init() {

        userEntity = createUserMock();
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(userRoleRepository.findByRole(any())).thenReturn(Optional.of(userEntity
                .getUserRoles().get(0)));
        when(userRepository.save(any())).thenReturn(userEntity);
    }

    @AfterEach
    void end() {
    }

    private UserEntity createUserMock() {

        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .role(RoleEnum.BASIC_USER)
                .build();

        return UserEntity.builder()
                .userName("dido18")
                .firstName("dido")
                .lastName("dido")
                .password("12345")
                .userRoles(Collections.singletonList(userRoleEntity))
                .build();

    }

    @Test
    void testInit() {

        when(userRepository.count()).thenReturn(0L);

        userService.init();

        verify(userRepository, times(3)).save(any());
        verify(userRoleRepository, times(3)).save(any());
    }

    @Test
    void testRegisterAndLogin() {

        when(userDetailsService.loadUserByUsername(any()))
                .thenReturn(
                        new User(userEntity.getUserName(),
                                userEntity.getPassword(),
                                Collections
                                        .singletonList(new SimpleGrantedAuthority("ROLE_"
                                                + RoleEnum.BASIC_USER.name()))));

        UserRegisterDto userRegisterDto = mapper.map(userEntity, UserRegisterDto.class);
        userRegisterDto.setConfirmPassword(userRegisterDto.getPassword());
        userRegisterDto.setEmail(userEntity.getUserName());

        boolean res = userService.registerAndLogin(userRegisterDto);

        assertTrue(res);

    }

    @Test
    void testRegisterAndLoginDiffConfirmPass() {

        UserEntity userEntity = createUserMock();

        when(userRepository.save(any())).thenReturn(userEntity);
        when(userDetailsService.loadUserByUsername(any()))
                .thenReturn(
                        new User(userEntity.getUserName(),
                                userEntity.getPassword(),
                                Collections
                                        .singletonList(new SimpleGrantedAuthority("ROLE_"
                                                + RoleEnum.BASIC_USER.name()))));

        UserRegisterDto userRegisterDto = mapper.map(userEntity, UserRegisterDto.class);
        userRegisterDto.setConfirmPassword(userRegisterDto.getPassword()+"12");
        userRegisterDto.setEmail(userEntity.getUserName());

        boolean res = userService.registerAndLogin(userRegisterDto);

        assertFalse(res);

    }

    @Test
    void testGetAllUsers() {

        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));
        List<UserEntity> users = userService.getAllUsers();
        assertTrue(users.size() > 0);

    }

    @Test
    void testGetUserByUserName() {
        when(userRepository.findByUserName(any())).thenReturn(Optional.ofNullable(userEntity));
      UserEntity user = userService.getUserByUserName(userEntity.getUserName());

      assertEquals(userEntity.getUserName(), user.getUserName());
      assertEquals(userEntity.getId(), user.getId());
    }

    @Test
    void testGetUserById() {
        UserEntity user = userService.getUserById(userEntity.getId());

        assertEquals(userEntity.getUserName(), user.getUserName());
        assertEquals(userEntity.getId(), user.getId());
    }

    @Test
    void testUpdateUser() {
        String expectedUserName = "funky";
        userEntity.setUserName(expectedUserName);
        when(userRepository.save(any())).thenReturn(userEntity);
        boolean res = userService.updateUser(userEntity);

        UserEntity updatedUserEntity = userService.getUserById(userEntity.getId());
        assertTrue(res);
        assertEquals(expectedUserName, updatedUserEntity.getUserName());
    }

    @Test
    void testUpdateNullUser() {
        boolean res = userService.updateUser(null);
        assertFalse(res);
    }

    @Test
    void testUpdateUserRole() {

        userEntity.setUserRoles(new ArrayList<>());
        userEntity.getUserRoles().add(UserRoleEntity.builder()
                        .role(RoleEnum.ADMIN)
                .build());

        when(userRoleRepository.findByRole(any())).thenReturn(Optional.of(userEntity
                .getUserRoles().get(0)));
        boolean res = userService.updateUserRole(userEntity.getId(), RoleEnum.ADMIN.name());

        UserEntity updatedUserEntity = userService.getUserById(userEntity.getId());

        assertTrue(res);
        assertEquals(RoleEnum.ADMIN.name(),
                updatedUserEntity.getUserRoles().get(0).getRole().name());
    }

    @Test
    void testUpdateUserRoleNoUser() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());
        boolean res = userService.updateUserRole(909l, RoleEnum.ADMIN.name());
        assertFalse(res);
    }

}
