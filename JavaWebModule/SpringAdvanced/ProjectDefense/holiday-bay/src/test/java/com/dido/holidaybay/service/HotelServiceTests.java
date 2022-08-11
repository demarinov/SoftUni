package com.dido.holidaybay.service;

import com.dido.holidaybay.client.HotelClient;
import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.model.dto.RoomDto;
import com.dido.holidaybay.model.enums.RoomTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class HotelServiceTests {

    @Mock
    private HotelClient hotelClient;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    void init() {
    }

    @Test
    void testGetHotelById() {
        Mockito.when(this.hotelClient.getHotelById(anyLong())).thenReturn(mockHotelDto().get(0));

        List<HotelDto> expectedHotels =  mockHotelDto();
        HotelDto actualHotel = hotelService.getHotelById(1L);

        verify(hotelClient).getHotelById(anyLong());
        assertEquals(expectedHotels.get(0).getName(), actualHotel.getName());
        assertEquals(expectedHotels.get(0).getAddress(), actualHotel.getAddress());
    }

    @Test
    void testGetHotels() {

        Mockito.when(this.hotelClient.getHotels()).thenReturn(mockHotelDto());

        List<HotelDto> expectedHotels =  mockHotelDto();
        List<HotelDto> actualHotels = hotelService.getHotels();
        verify(this.hotelClient).getHotels();

        assertEquals(expectedHotels.size(), actualHotels.size());
        assertEquals(expectedHotels.get(0).getName(), actualHotels.get(0).getName());
        assertEquals(expectedHotels.get(0).getAddress(), actualHotels.get(0).getAddress());
        assertEquals(expectedHotels.get(1).getName(), actualHotels.get(1).getName());
    }

    @Test
    void testGetHotelsWithFreeRooms() {

        Mockito.when(this.hotelClient.getHotelsWithFreeRooms()).thenReturn(mockHotelDto());

        List<HotelDto> expectedHotels =  mockHotelDto();
        List<HotelDto> actualHotels = hotelService.getHotelsWithFreeRooms();

        verify(hotelClient).getHotelsWithFreeRooms();
        assertEquals(expectedHotels.size(), actualHotels.size());
        assertEquals(expectedHotels.get(0).getName(), actualHotels.get(0).getName());
        assertEquals(expectedHotels.get(0).getAddress(), actualHotels.get(0).getAddress());
        assertEquals(expectedHotels.get(1).getName(), actualHotels.get(1).getName());
        assertEquals(expectedHotels.get(0).getRooms().get(0).getId(), 1l);
        assertEquals(expectedHotels.get(0).getRooms().get(0).getRoomType(),
                RoomTypeEnum.ONE_BED_ECONOMY.name());
        assertEquals(expectedHotels.get(0).getRooms().get(0).getId(), 1l);
        assertEquals(expectedHotels.get(0).getRooms().get(1).getId(), 2l);
    }

    private List<HotelDto> mockHotelDto() {

        List<HotelDto> hotelDtoList = new ArrayList<>();

        RoomDto roomDtoOne = RoomDto.builder()
                .id(1l)
                .isFree(true)
                .price(100d)
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY.name())
                .build();

        RoomDto roomDtoTwo = RoomDto.builder()
                .id(2l)
                .isFree(true)
                .price(105d)
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY.name())
                .build();

        HotelDto hotelDto = HotelDto.builder()
                .id(1l)
                .name("Santa Veronica")
                .address("New str. 1")
                .rooms(Arrays.asList(roomDtoOne, roomDtoTwo))
                .build();

        HotelDto hotelDtoTwo = HotelDto.builder()
                .id(2l)
                .name("Santa Veronica 2")
                .address("New str. 2")
                .build();

        hotelDtoList.add(hotelDto);
        hotelDtoList.add(hotelDtoTwo);

        return hotelDtoList;
    }

}
