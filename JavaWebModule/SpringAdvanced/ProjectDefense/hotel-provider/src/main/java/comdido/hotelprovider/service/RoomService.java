package comdido.hotelprovider.service;

import comdido.hotelprovider.model.dto.RoomDto;
import comdido.hotelprovider.model.entity.RoomEntity;
import comdido.hotelprovider.model.enums.RoomTypeEnum;
import comdido.hotelprovider.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomDto getRoom(Long roomId) {

        return roomRepository.findById(roomId).map(this::map).orElse(null);
    }

    private RoomDto map(RoomEntity roomEntity) {

        return RoomDto.builder()
                .id(roomEntity.getId())
                .price(roomEntity.getPrice())
                .isFree(roomEntity.isFree())
                .number(roomEntity.getNumber())
                .imageUrl(roomEntity.getImageUrl())
                .created(roomEntity.getCreated())
                .build();
    }

    public RoomDto getRoomByType(String roomType) {
        return roomRepository.findFirstByRoomTypeAndIsFree(RoomTypeEnum.valueOf(roomType), true)
                .map(this::map).orElse(null);

    }

    public RoomDto getRoomByTypeAndHotel(String roomType, String hotelName) {
        return roomRepository.findAllByRoomTypeAndIsFree(RoomTypeEnum.valueOf(roomType), true)
                .stream()
                .filter(room -> room.getHotel().getName().equals(hotelName))
                .map(this::map).findFirst().orElse(null);

    }

    public boolean updateRoomStatus(Long roomId, boolean isFree) {

        RoomEntity roomEntity = roomRepository.findById(roomId).orElse(null);

        if (roomEntity != null) {
            roomEntity.setFree(isFree);
            roomRepository.save(roomEntity);
            return true;
        }

        return false;
    }
}
