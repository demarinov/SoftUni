package comdido.hotelprovider.model.dto;

import lombok.*;

import java.time.LocalDateTime;


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

    private HotelDto hotelDto;

    private LocalDateTime created;
    private LocalDateTime modified;

    private double price;

    private String imageUrl;
}
