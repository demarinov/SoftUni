package com.dido.holidaybay.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VideoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testVideo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/video/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("video"))
                .andExpect(model().attributeExists("videos"));
    }
}
