package comdido.hotelprovider.web;

import comdido.hotelprovider.model.dto.HotelDto;
import comdido.hotelprovider.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{roomId}")
    @CrossOrigin
    public HotelDto getHotelByRoomId(@PathVariable("roomId") Long roomId) {

        return hotelService.getHotelByRoomId(roomId);
    }

    @GetMapping("/all")
    public List<HotelDto> allHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/all/free-rooms")
    public List<HotelDto> allHotelsWithFreeRooms() {
        return hotelService.getAllHotelsWithFreeRooms();
    }

    @GetMapping("/details")
    public HotelDto getHotelById(@RequestParam("id") Long id) {

        return hotelService.getHotelById(id);
    }


    @PostMapping("/create")
    public boolean create(@RequestBody HotelDto hotelDto) {
        hotelService.create(hotelDto);
        return true;
    }

}
