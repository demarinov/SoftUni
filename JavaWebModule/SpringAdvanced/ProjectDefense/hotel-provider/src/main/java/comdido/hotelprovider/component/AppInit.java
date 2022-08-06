package comdido.hotelprovider.component;

import comdido.hotelprovider.model.dto.HotelDto;
import comdido.hotelprovider.model.entity.HotelEntity;
import comdido.hotelprovider.model.entity.RoomEntity;
import comdido.hotelprovider.model.enums.RoomTypeEnum;
import comdido.hotelprovider.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppInit implements CommandLineRunner {

    private final HotelService hotelService;

    @Override
    public void run(String... args) throws Exception {

        List<HotelDto> hotels = hotelService.getAllHotels();

        if (!hotels.isEmpty()) {
            return;
        }

        HotelEntity hotelOne =
                HotelEntity.builder()
                .name("Kubanito")
                .address("Sunny Beach, Burgas")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                        .content("Hotel Kubanito has a selection of leisure facilities including an attractive outdoor swimming pool area comprising of three interconnected swimming pools, seven water slides, kids and splash sections and a poolside bar for a fun filled day in the sunshine. There is also a fitness as well as a Turkish bath and sauna.")
                .imageUrl("https://media-cdn.tripadvisor.com/media/photo-s/1d/33/76/70/aquapark-kuban.jpg")
                .build();


        HotelEntity hotelTwo = HotelEntity.builder()
                .name("Sol Marino")
                .address("Nesebar, Burgas")
                .created(LocalDateTime.now())
                .content("With its unique location and quality of services, Sol Marino is a very good choice for relaxing, beach and active holidays and also for business events. Modern conference rooms for formal meetings, elegant restaurant and bars, comfortable rooms with stunning views will make your stay unforgettable.")
                .modified(LocalDateTime.now())
                .imageUrl("https://www.melia.com/dam/jcr:d9f5711d-811d-4f24-9680-5951d5e227cd/02SolNessebarMare-GeneralPoolNight-2.jpg")
                .build();

        HotelEntity hotelThree = HotelEntity.builder()
                .name("Helena Beach")
                .address("Sozopol, Burgas")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .content("The hotel features room service and a concierge. Plus, Helena Beach Hotel offers a pool and free breakfast, providing a pleasant respite from your busy day. For guests with a vehicle, free parking is available. If you like pubs, Helena Beach Hotel is conveniently located near Tsy Manin - Bar Chill out.")
                .imageUrl("https://imgcy.trivago.com/c_lfill,d_dummy.jpeg,e_sharpen:60,f_auto,h_450,q_auto,w_450/itemimages/32/90/32908_v4.jpeg")
                .build();


        RoomEntity roomOne = RoomEntity.builder()
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .hotel(hotelOne)
                .imageUrl("https://q-xx.bstatic.com/xdata/images/hotel/max500/265799048.jpg?k=b674390c1fc155c50d90b3c4737f1b9ac35ab8d613b5a9b1d6f6022b59889c8f&o=")
                .number("1A")
                .price(50.0d)
                .isFree(true)
                .build();
        RoomEntity roomTwo = RoomEntity.builder()
                .roomType(RoomTypeEnum.TWO_BEDS_ECONOMY)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isFree(true)
                .number("2A")
                .price(100.0d)
                .imageUrl("https://q-xx.bstatic.com/xdata/images/hotel/max500/119001039.jpg?k=39198ba7f676e17e918968b0188dc4d2f27d5915143ff04eac5c7fd043989a3c&o=")
                .hotel(hotelOne)
                .build();

        RoomEntity roomOneAgain = RoomEntity.builder()
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isFree(true)
                .number("1A")
                .price(55.0d)
                .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/360276361.jpg?k=5920e060985494abc9e66adf2bc3d896efc8e89da1540faa5f52068241c728b3&o=&hp=1")
                .hotel(hotelTwo)
                .build();
        RoomEntity roomTwoAgain = RoomEntity.builder()
                .roomType(RoomTypeEnum.TWO_BEDS_ECONOMY)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isFree(true)
                .number("2A")
                .price(110.0d)
                .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/371289930.jpg?k=8a0223933f714fbaf63f3ebffd2dfec3619acc025ab3ff88ecc484cf7f434426&o=&hp=1")
                .hotel(hotelTwo)
                .build();

        RoomEntity roomOneLux = RoomEntity.builder()
                .roomType(RoomTypeEnum.ONE_BED_LUX)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isFree(true)
                .number("1A")
                .price(150.0d)
                .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/348236218.jpg?k=f06b0db5a821e38d5b2c409ae53104459e9b164d56ddad422a0b596823e80403&o=&hp=1")
                .hotel(hotelThree)
                .build();
        RoomEntity roomTwoLux = RoomEntity.builder()
                .roomType(RoomTypeEnum.TWO_BEDS_LUX)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isFree(true)
                .number("2A")
                .price(200.0d)
                .imageUrl("https://cf.bstatic.com/xdata/images/hotel/max1024x768/348236218.jpg?k=f06b0db5a821e38d5b2c409ae53104459e9b164d56ddad422a0b596823e80403&o=&hp=1")
                .hotel(hotelThree)
                .build();

        hotelOne.setRooms(Arrays.asList(roomOne,roomTwo));
        hotelTwo.setRooms(Arrays.asList(roomOneAgain,roomTwoAgain));
        hotelThree.setRooms(Arrays.asList(roomOneLux, roomTwoLux));

        hotelService.create(hotelOne);
        hotelService.create(hotelTwo);
        hotelService.create(hotelThree);
    }
}
