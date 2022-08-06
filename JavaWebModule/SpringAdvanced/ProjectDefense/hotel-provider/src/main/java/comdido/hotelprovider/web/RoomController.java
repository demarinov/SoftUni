package comdido.hotelprovider.web;

import comdido.hotelprovider.model.dto.RoomDto;
import comdido.hotelprovider.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
    @CrossOrigin
    public RoomDto getRoom(@PathVariable("id") Long roomId) {

        return roomService.getRoom(roomId);
    }

    @GetMapping("type")
    @CrossOrigin
    public RoomDto getRoomByType(@RequestParam("type") String roomType) {

        return roomService.getRoomByType(roomType);
    }

    @GetMapping("")
    @CrossOrigin
    public RoomDto getRoomByTypeAndHotel(@RequestParam("hotel") String hotelName, @RequestParam("type") String roomType) {

        return roomService.getRoomByTypeAndHotel(roomType, hotelName);
    }

    @PostMapping("/update-status")
    public boolean updateRoomStatus(@RequestParam("id") Long roomId, @RequestParam("free") boolean isFree) {

        return roomService.updateRoomStatus(roomId, isFree);
    }
}
