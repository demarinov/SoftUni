package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.model.dto.RoomDto;
import com.dido.holidaybay.model.enums.RoomTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    public static MockWebServer mockBackEnd;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(7777);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {

    }

    @Test
    void testHotels() throws Exception {

        HotelDto mockHotelDto = HotelDto.builder()
                .id(1L)
                .name("hotel-test")
                .content("whatever str. 55")
                .build();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(Collections.singletonList(mockHotelDto)))
                .addHeader("Content-Type", "application/json"));

        mockMvc.perform(MockMvcRequestBuilders.get("/hotels/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("hotels"))
                .andExpect(model().attributeExists("hotels"));
    }

    @Test
    void testHotel() throws Exception {

        RoomDto roomDto = RoomDto.builder()
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY.name())
                .id(1L)
                .price(20d)
                .isFree(true)
                .imageUrl("")
                .number("1A")
                .build();

        HotelDto mockHotelDto = HotelDto.builder()
                .id(1L)
                .rooms(Collections.singletonList(roomDto))
                .name("hotel-test")
                .content("whatever str. 55")
                .build();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockHotelDto))
                .addHeader("Content-Type", "application/json"));

        mockMvc.perform(MockMvcRequestBuilders.get("/hotels/details?id="+1L))
                .andExpect(status().isOk())
                .andExpect(view().name("hotel-details"))
                .andExpect(model().attributeExists("hotel"));
    }

}
