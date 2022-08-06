package comdido.hotelprovider.service;

import comdido.hotelprovider.model.dto.HotelDto;
import comdido.hotelprovider.model.dto.RoomDto;
import comdido.hotelprovider.model.entity.HotelEntity;
import comdido.hotelprovider.model.entity.RoomEntity;
import comdido.hotelprovider.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(this::map)
                .collect(Collectors.toList());
    }

    public List<HotelDto> getAllHotelsWithFreeRooms() {
        return hotelRepository.findAll().stream()
                .filter(this::hasFreeRooms)
                .map(this::mapFreeRooms)
                .collect(Collectors.toList());
    }

    public boolean create(HotelDto hotelDto) {

        if (hotelDto != null) {

            for (RoomDto roomDto : hotelDto.getRooms()) {
                roomDto.setHotelDto(hotelDto);
                roomDto.setCreated(LocalDateTime.now());
                roomDto.setModified(LocalDateTime.now());
            }

            HotelEntity hotel = modelMapper.map(hotelDto, HotelEntity.class);
            hotel.setCreated(LocalDateTime.now());
            hotel.setModified(LocalDateTime.now());
            hotelRepository.save(hotel);

            return true;
        }

        return false;
    }

    public boolean create(HotelEntity hotelEntity) {

        if (hotelEntity != null) {
            hotelRepository.save(hotelEntity);

            return true;
        }

        return false;
    }

    private boolean hasFreeRooms(HotelEntity hotelEntity) {

        for(RoomEntity room: hotelEntity.getRooms()) {

            if (room.isFree()) {
                return true;
            }
        }

        return false;
    }

    private HotelDto map(HotelEntity hotelEntity) {

        List<RoomDto> roomDtos = new ArrayList<>();
        for (RoomEntity roomEntity : hotelEntity.getRooms()) {
            RoomDto roomDto = RoomDto.builder()
                    .id(roomEntity.getId())
                    .number(roomEntity.getNumber())
                    .roomType(roomEntity.getRoomType().name())
                    .isFree(roomEntity.isFree())
                    .price(roomEntity.getPrice())
                    .imageUrl(roomEntity.getImageUrl())
                    .build();
            roomDtos.add(roomDto);
        }

        return HotelDto.builder()
                .id(hotelEntity.getId())
                .name(hotelEntity.getName())
                .address(hotelEntity.getAddress())
                .rooms(roomDtos)
                .content(hotelEntity.getContent())
                .imageUrl(hotelEntity.getImageUrl())
                .build();
    }

    private HotelDto mapFreeRooms(HotelEntity hotelEntity) {

        List<RoomDto> roomDtos = new ArrayList<>();
        for (RoomEntity roomEntity : hotelEntity.getRooms()) {

            if (!roomEntity.isFree()) {
                continue;
            }

            RoomDto roomDto = RoomDto.builder()
                    .id(roomEntity.getId())
                    .number(roomEntity.getNumber())
                    .roomType(roomEntity.getRoomType().name())
                    .isFree(roomEntity.isFree())
                    .price(roomEntity.getPrice())
                    .imageUrl(roomEntity.getImageUrl())
                    .build();
            roomDtos.add(roomDto);
        }

        return HotelDto.builder()
                .id(hotelEntity.getId())
                .name(hotelEntity.getName())
                .address(hotelEntity.getAddress())
                .rooms(roomDtos)
                .content(hotelEntity.getContent())
                .imageUrl(hotelEntity.getImageUrl())
                .build();
    }

    public HotelDto getHotelById(Long id) {

        return hotelRepository.findById(id).map(this::map).orElse(null);
    }

    public HotelDto getHotelByRoomId(Long roomId) {

        List<HotelDto> hotelDtos = getAllHotels();

        return hotelDtos.stream().filter(hotel -> {

            List<RoomDto> roomDtos = hotel.getRooms();

            return roomDtos.stream().anyMatch(room -> room.getId().equals(roomId));
        }).findFirst().orElse(null);
    }
}
