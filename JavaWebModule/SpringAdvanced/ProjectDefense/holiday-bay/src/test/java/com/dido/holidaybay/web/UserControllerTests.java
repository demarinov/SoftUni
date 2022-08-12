package com.dido.holidaybay.web;

import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.model.enums.RoleEnum;
import com.dido.holidaybay.repository.UserRepository;
import com.dido.holidaybay.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        UserEntity user = createUserMock();
        if (!userRepository.findByUserName(user.getUserName()).isPresent()) {
            user.setId(null);
            user = userRepository.save(user);
        }

    }

    private UserEntity createUserMock() {

        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .role(RoleEnum.ADMIN)
                .build();

        if (userRoleRepository.findByRole(RoleEnum.ADMIN).isPresent()) {
            userRoleEntity = userRoleRepository.findByRole(RoleEnum.ADMIN).get();
        }

        return UserEntity.builder()
                .userName("admin")
                .firstName("dido")
                .lastName("dido")
                .password("12345")
                .userRoles(Collections.singletonList(userRoleEntity))
                .build();

    }

    @Test
//    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void testRegisterGet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"))
                .andExpect(model().attributeExists("userRegistrationModel"));
    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testRegisterLogged() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());

    }

    @Test
//    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void testRegisterPostOk() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", "Chocho")
                        .param("lastName", "Chochev")
                        .param("password", "12345")
                        .param("confirmPassword", "12345")
                        .param("email", "chochko@mail.com")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());

        UserEntity user = userRepository.findByUserName("chochko@mail.com").orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Test
//    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void testRegisterPostDifferentPasswords() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", "Chocho")
                        .param("lastName", "Chochev")
                        .param("password", "12345")
                        .param("confirmPassword", "1234")
                        .param("email", "chochko@mail.com")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/users/register"))
                .andExpect(status().isFound());

    }


    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testRegisterPostWithUserLogged() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());

    }

    @Test
//    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void testRegisterPostWithNoData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/users/register"))
                .andExpect(status().isFound());

    }

    @Test
//    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void testLogin() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));

    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testLoginUserLogged() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());


    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testFailedLogin() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/users/login"))
                .andExpect(status().isFound());


    }


    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testProfile() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("user"));

    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testAdmin() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("voucherModel"))
                .andExpect(model().attributeExists("adminModel"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("vouchers"));

    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testAdminRoleChange() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/change-role")
                        .param("userId","1")
                        .param("roleType", "BASIC_USER")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/users/admin"))
                .andExpect(status().isFound());

    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testAdminRoleChangeMissingUserId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/change-role")
                        .param("roleType", "BASIC_USER")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/users/admin"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("adminModel"));

    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testAdminDeactivateVoucher() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/voucher-deactivate")
                        .param("voucherId", "1")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/vouchers/deactivate"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("voucherModel"));

    }

    @Test
    @WithMockUser(username="admin@mail.com",roles={"USER","ADMIN"})
    void testAdminDeactivateVoucherMissingData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/voucher-deactivate")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/users/admin"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("voucherModel"));

    }


}
