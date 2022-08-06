package com.dido.holidaybay.model.dto;

import lombok.*;


// dto to be used in backend service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    private String number;
    private String roomType;
    private boolean isFree;
    private double price;

    // of given type
    private int roomCount;

    private String imageUrl;

}
