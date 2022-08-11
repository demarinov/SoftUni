package com.dido.holidaybay.service;

import com.dido.holidaybay.client.RoomClient;
import com.dido.holidaybay.event.BookingExpirationEvent;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.RoomDto;
import com.dido.holidaybay.model.entity.BookingEntity;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingServiceTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomClient roomClient;

    @Mock
    private BankingService bankingService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void init() {

    }

    @Test
    void testGetUserBookings() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .durationInNights(2l)
                .price(2d)
                .active(true)
                .id(1l)
                .build();

        Page<BookingEntity> bookingEntityPage = new PageImpl(Arrays.asList(bookingEntity));

        when(bookingRepository.findByActiveAndUserId(eq(true), any(), any()))
                .thenReturn(bookingEntityPage);

        when(roomClient.getRoomById(any())).thenReturn(RoomDto.builder().build());

        Page<BookingDto> bookingDto = bookingService.getUserBookings(UserEntity.builder().build()
                , null);
        assertEquals(1, bookingDto.getTotalPages());

    }

    @Test
    void testGetUserBookingsHistory() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .durationInNights(2l)
                .price(2d)
                .active(false)
                .id(1l)
                .build();

        Page<BookingEntity> bookingEntityPage = new PageImpl(Arrays.asList(bookingEntity));

        when(bookingRepository.findByActiveAndUserId(eq(false), any(), any()))
                .thenReturn(bookingEntityPage);

        when(roomClient.getRoomById(any())).thenReturn(RoomDto.builder().build());

        Page<BookingDto> bookingDto = bookingService.getUserBookingsHistory(UserEntity.builder().build()
                , null);
        assertEquals(1, bookingDto.getTotalPages());

    }

    @Test
    void testGetBooking() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .durationInNights(2l)
                .price(2d)
                .active(true)
                .id(1l)
                .build();

        when(bookingRepository.findById(any()))
                .thenReturn(java.util.Optional.ofNullable(bookingEntity));

        BookingEntity foundBookingEntity = bookingService.getBooking(bookingEntity.getId());
        assertNotNull(foundBookingEntity);
        assertEquals(bookingEntity.getId(), foundBookingEntity.getId());

    }

    @Test
    void testUpdateBookingOk() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .durationInNights(2l)
                .price(2d)
                .active(true)
                .id(1l)
                .build();

        when(bookingRepository.save(any()))
                .thenReturn(bookingEntity);

        boolean res = bookingService.updateBooking(bookingEntity);
        assertTrue(res);

    }

    @Test
    void testUpdateBookingNull() {

        boolean res = bookingService.updateBooking(null);
        assertFalse(res);

    }

    @Test
    void testExpiredBookingOk() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .durationInNights(2l)
                .price(2d)
                .active(true)
                .id(1l)
                .build();

        BookingExpirationEvent bookingExpirationEvent =
                new BookingExpirationEvent(BookingServiceTests.class.getSimpleName()
                        , LocalDateTime.now());
        bookingExpirationEvent.setBookingIds(new ArrayList<>());
        bookingExpirationEvent.getBookingIds().add(bookingEntity.getId());

        when(bookingRepository.findById(any()))
                .thenReturn(java.util.Optional.ofNullable(bookingEntity));
        when(bookingRepository.save(any()))
                .thenReturn(bookingEntity);

        boolean res = bookingService.expiredBooking(bookingExpirationEvent);
        assertTrue(res);

        assertFalse(bookingEntity.isActive());

    }

    @Test
    void testAddBookingNull() {

        boolean res = bookingService.addBooking(null, null);

        assertFalse(res);
    }

    @Test
    void testAddBookingNoMoney() {

        when(bankingService.withdraw(anyDouble(),any()))
                .thenReturn(false);
        when(bookingRepository.save(any()))
                .thenReturn(null);

        boolean res = bookingService.addBooking(BookingDto.builder()
                        .roomId(1l)
                        .durationInNights(2l)
                        .price(200d)
                        .startDate(LocalDateTime.now().toLocalDate())
                        .build()
                , UserEntity.builder().build());

        assertFalse(res);
    }

    @Test
    void testAddBookingOk() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .id(1l)
                .build();
        when(bankingService.withdraw(anyDouble(),any()))
                .thenReturn(true);
        when(bookingRepository.save(any()))
                .thenReturn(bookingEntity);
        when(roomClient.getRoomById(any()))
                .thenReturn(RoomDto.builder().build());


        BookingDto bookingDto = BookingDto.builder()
                .roomId(1l)
                .id(1l)
                .durationInNights(2l)
                .price(200d)
                .startDate(LocalDateTime.now().toLocalDate())
                .build();

        boolean res = bookingService.addBooking(bookingDto
                , UserEntity.builder()
                                .bookings(new ArrayList<>())
                        .build());

        assertTrue(res);
        assertEquals(bookingDto.getId(), bookingEntity.getId());
    }
}
