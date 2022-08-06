package com.dido.holidaybay.service;

import com.dido.holidaybay.client.HotelClient;
import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.model.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelClient hotelClient;

    public List<HotelDto> getHotels() {
        return hotelClient.getHotels();
    }

    public List<HotelDto> getHotelsWithFreeRooms() {

        List<HotelDto> hotelDtos = hotelClient.getHotelsWithFreeRooms();

        hotelDtos = findHotelsWithDistinctRooms(hotelDtos);

        return hotelDtos;
    }

    private List<HotelDto> findHotelsWithDistinctRooms(List<HotelDto> hotelDtos) {
        Set<String> hotelDtoSet = new TreeSet<>();

        StringBuilder builder = new StringBuilder();
        for(HotelDto hotelDto : hotelDtos) {
            List<RoomDto> roomDtoList = new ArrayList<>();
            Map<String, Integer> roomTypeCountMap = new TreeMap<>();

            for(RoomDto roomDto : hotelDto.getRooms()) {
                builder.append(hotelDto.getName());
                builder.append(roomDto.getRoomType());

                roomTypeCountMap.putIfAbsent(roomDto.getRoomType(), 0);
                roomTypeCountMap.put(roomDto.getRoomType()
                        ,roomTypeCountMap.get(roomDto.getRoomType())+1);

                if (!hotelDtoSet.contains(builder.toString())) {

                    hotelDtoSet.add(builder.toString());
                    RoomDto newRoomDto = RoomDto.builder()
                            .isFree(roomDto.isFree())
                            .roomType(roomDto.getRoomType())
                            .price(roomDto.getPrice())
                            .roomCount(1)
                            .build();
                    roomDtoList.add(newRoomDto);
                }

                builder.delete(0, builder.length());
            }

            for(RoomDto roomDto : roomDtoList) {

                if (roomTypeCountMap.containsKey(roomDto.getRoomType())) {
                    roomDto.setRoomCount(roomTypeCountMap.get(roomDto.getRoomType()));
                }
            }

            hotelDto.setRooms(roomDtoList);

        }

        return hotelDtos;
    }


    public HotelDto getHotelById(Long id) {

        return hotelClient.getHotelById(id);
    }
}
