package com.dido.holidaybay.model.dto;

import lombok.*;

import java.util.List;

// dto to be used in hotel backend
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {

    private Long id;

    private String name;

    private String address;

    private List<RoomDto> rooms;

    private String imageUrl;

    private String content;
}
