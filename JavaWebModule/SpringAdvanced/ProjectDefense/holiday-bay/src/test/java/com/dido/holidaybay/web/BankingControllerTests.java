package com.dido.holidaybay.web;

import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.repository.UserRepository;
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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BankingControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testCashier() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/banking"))
                .andExpect(status().isOk())
                .andExpect(view().name("cashier"))
                .andExpect(model().attributeExists("depositModel"))
                .andExpect(model().attributeExists("withdrawModel"))
                .andExpect(model().attributeExists("funds"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testGetFunds() throws Exception {
        UserEntity user = userRepository.findByUserName("admin@mail.com").orElse(null);

        double amount = 0d;
        if (user != null) {
            amount = user.getBankAccount().getAmount();
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/banking/funds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depositAmount", is(amount)));

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testDeposit() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/banking/deposit")
                        .param("depositAmount", "1000")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("funds"));

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testDepositNoParam() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/banking/deposit")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("depositModel"));

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testWithdraw() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/banking/withdraw")
                        .param("withdrawAmount", "1000")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("funds"));

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testWithdrawNoParam() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/banking/withdraw")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("withdrawModel"));

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testWithdrawNegativeAmount() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/banking/withdraw")
                        .param("withdrawAmount", "-1000")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("withdrawModel"));

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testWithdrawNotEnoughFunds() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/banking/withdraw")
                        .param("withdrawAmount", "20000")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("not_enough_money"));

    }
}
