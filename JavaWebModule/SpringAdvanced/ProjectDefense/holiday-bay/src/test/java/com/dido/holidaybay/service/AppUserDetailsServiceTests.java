package com.dido.holidaybay.service;

import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.model.enums.RoleEnum;
import com.dido.holidaybay.repository.UserRepository;
import com.dido.holidaybay.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppUserDetailsServiceTests {

    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private UserEntity userEntity;

    @BeforeEach
    void init() {
        appUserDetailsService = new AppUserDetailsService(userRepository);

        userEntity = createUserMock();

        userRepository.save(userEntity);
    }

    @AfterEach
    void end() {
        userRepository.delete(userEntity);
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
    void testLoadUserByUserName() {

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(userEntity.getUserName());

        assertNotNull(userDetails);
        assertEquals(userEntity.getUserName(), userDetails.getUsername());
    }
}
