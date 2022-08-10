package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.UserRegisterDto;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.model.enums.RoleEnum;
import com.dido.holidaybay.repository.BankingRepository;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    // using autowired repo and not mock just for unit testing and exercise in this one...
    // ... after running the tests maybe it is a good idea to drop dbs and run the apps again

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    private UserEntity userEntity;

    @Autowired
    private ModelMapper mapper;


    @BeforeEach()
    void init() {

        if (userRepository.count() == 0) {
            userEntity = createUserMock();

            userEntity = userRepository.save(userEntity);
        } else {
            userEntity = userRepository.findAll().get(0);
        }

    }

    @AfterEach
    void end() {
        userRepository.deleteAll();
    }

    private UserEntity createUserMock() {

        Optional<UserRoleEntity> userRoleEntityOpt = userRoleRepository.findByRole(RoleEnum.BASIC_USER);
        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .role(RoleEnum.BASIC_USER)
                .build();
        if (userRoleEntityOpt.isPresent()) {
            userRoleEntity = userRoleEntityOpt.get();
        }

        return UserEntity.builder()
                .userName("dido18")
                .firstName("dido")
                .lastName("dido")
                .password("12345")
                .userRoles(Arrays.asList(userRoleEntity))
                .build();

    }

    @Test
    void testInit() {
        // for testing only ...
        userRepository.deleteAll();
        userRoleRepository.deleteAll();

        userService.init();

        assertTrue(userRepository.count() > 0);
        assertTrue(userRoleRepository.count() > 0);
    }

    @Test
    void testRegisterAndLogin() {

        userRepository.deleteAll();

        UserEntity userEntity = createUserMock();

        userEntity = userRepository.save(userEntity);

        UserRegisterDto userRegisterDto = mapper.map(userEntity, UserRegisterDto.class);
        userRegisterDto.setConfirmPassword(userRegisterDto.getPassword());
        userRegisterDto.setEmail(userEntity.getUserName());
        userRepository.delete(userEntity);

        boolean res = userService.registerAndLogin(userRegisterDto);

        UserEntity newUser = userRepository
                .findByUserName(userEntity.getUserName()).orElse(null);

        assertTrue(res);
        assertEquals(userEntity.getUserName(), newUser.getUserName());

    }

    @Test
    void testRegisterAndLoginDiffConfirmPass() {

        userRepository.deleteAll();

        UserEntity userEntity = createUserMock();

        userEntity = userRepository.save(userEntity);

        UserRegisterDto userRegisterDto = mapper.map(userEntity, UserRegisterDto.class);
        userRegisterDto.setConfirmPassword(userRegisterDto.getPassword()+"12");
        userRegisterDto.setEmail(userEntity.getUserName());
        userRepository.delete(userEntity);

        boolean res = userService.registerAndLogin(userRegisterDto);

        assertFalse(res);

    }

    @Test
    void testGetAllUsers() {

        List<UserEntity> users = userService.getAllUsers();
        assertTrue(users.size() > 0);

    }

    @Test
    void testGetUserByUserName() {
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

        boolean res = userService.updateUserRole(userEntity.getId(), RoleEnum.ADMIN.name());

        UserEntity updatedUserEntity = userService.getUserById(userEntity.getId());

        assertTrue(res);
        assertEquals(RoleEnum.ADMIN.name(),
                updatedUserEntity.getUserRoles().get(0).getRole().name());
    }

    @Test
    void testUpdateUserRoleNoUser() {

        boolean res = userService.updateUserRole(909l, RoleEnum.ADMIN.name());

        UserEntity updatedUserEntity = userService.getUserById(userEntity.getId());

        assertFalse(res);
    }

}
