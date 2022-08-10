package com.dido.holidaybay.service;

import com.dido.holidaybay.client.RoomClient;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.VoucherDto;
import com.dido.holidaybay.model.entity.BookingEntity;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.VoucherEntity;
import com.dido.holidaybay.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VoucherServiceTests {

    @InjectMocks
    private VoucherService voucherService;

    @Autowired
    private ModelMapper mapper;

    @Mock
    private ModelMapper mockedMapper;

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private BookingService bookingService;

    @Mock
    private RoomClient roomClient;

    @BeforeEach
    void init() {

    }

    @Test
    void testGetVouchers() {

        UserEntity userEntity = UserEntity.builder()
                .userName("buddy")
                .build();
        BookingEntity bookingEntity = BookingEntity.builder()
                .user(userEntity)
                .build();

        VoucherEntity expiredVoucherEntity = VoucherEntity.builder()
                .endDate(null)
                .booking(bookingEntity)
                .id(2l)
                .build();

        Page<VoucherEntity> voucherEntityPage =
                new PageImpl(Arrays.asList(expiredVoucherEntity));
        when(voucherRepository.findByHasExpiredAndBookingUser(anyBoolean(), any(), any()))
                .thenReturn(voucherEntityPage);
        when(mockedMapper.map(any(), any())).thenReturn(VoucherDto.builder()
                .name(expiredVoucherEntity.getName())
                .id(expiredVoucherEntity.getId())
                .build());

        Page<VoucherDto> voucherDtoList = voucherService.getVouchers(userEntity, null);

        assertTrue(!voucherDtoList.isEmpty());

    }

    @Test
    void testGetExpiredVouchers() {

        UserEntity userEntity = UserEntity.builder()
                .userName("buddy")
                .build();
        BookingEntity bookingEntity = BookingEntity.builder()
                .user(userEntity)
                .build();

        VoucherEntity expiredVoucherEntity = VoucherEntity.builder()
                .endDate(null)
                .booking(bookingEntity)
                .id(2l)
                .build();

        when(voucherRepository.findAll()).thenReturn(Collections.singletonList(expiredVoucherEntity));
        when(mockedMapper.map(any(), any())).thenReturn(VoucherDto.builder()
                .name(expiredVoucherEntity.getName())
                .id(expiredVoucherEntity.getId())
                .build());

        List<VoucherDto> voucherDtoList = voucherService.getExpiredVouchers();

        assertTrue(voucherDtoList.size() > 0);

    }

    @Test
    void testGetUserVouchers() {

        UserEntity userEntity = UserEntity.builder()
                .userName("buddy")
                .build();
        BookingEntity bookingEntity = BookingEntity.builder()
                .user(userEntity)
                .build();

        VoucherEntity expiredVoucherEntity = VoucherEntity.builder()
                .endDate(LocalDateTime.now().plusDays(1))
                .booking(bookingEntity)
                .id(2l)
                .build();

        when(voucherRepository.findAll()).thenReturn(Collections.singletonList(expiredVoucherEntity));
        when(mockedMapper.map(any(), any())).thenReturn(VoucherDto.builder()
                        .name(expiredVoucherEntity.getName())
                        .id(expiredVoucherEntity.getId())
                .build());

        List<VoucherDto> voucherDtoList = voucherService.getVouchers(userEntity);

        assertTrue(voucherDtoList.size() > 0);

    }

    @Test
    void testIsVoucherExpired() {

        VoucherEntity voucherEntity = VoucherEntity.builder()
                .endDate(null)
                .build();

        assertTrue(voucherService.isVoucherExpired(voucherEntity));

        VoucherEntity voucherEntity2 = VoucherEntity.builder()
                .endDate(LocalDateTime.now().minusDays(1))
                .build();

        assertTrue(voucherService.isVoucherExpired(voucherEntity2));
    }

    @Test
    void testDeactivateVoucherIsNull() {

        assertFalse(voucherService.deactivateVoucher(VoucherDto.builder()
                        .id(500l)
                .build()));
    }

    @Test
    void testDeactivateVoucher() {


        VoucherEntity voucherEntity = VoucherEntity.builder()
                .name("voucher-test")
                .booking(BookingEntity.builder().build())
                .hasExpired(false)
                .build();

        when(bookingService.updateBooking(any())).thenReturn(true);
        when(roomClient.updateRoomStatus(anyLong(), anyBoolean())).thenReturn(true);
        when(voucherRepository.save(voucherEntity)).thenReturn(voucherEntity);
        when(voucherRepository.findById(any())).thenReturn(Optional.of(voucherEntity));

        VoucherDto voucherDto = VoucherDto.builder()
                .id(voucherEntity.getId())
                .hasExpired(voucherEntity.isHasExpired())
                .build();

        assertTrue(voucherService.deactivateVoucher(voucherDto));

        VoucherEntity deactivatedVoucherEntity = voucherRepository
                .findById(voucherEntity.getId()).orElse(null);

        assertEquals(voucherEntity.getId(), deactivatedVoucherEntity.getId());
        assertTrue(deactivatedVoucherEntity.isHasExpired());
    }

    @Test
    void testCreateNoBooking() {

        assertFalse(voucherService.create(BookingDto.builder()
                        .id(500l)
                .build()));


    }

    @Test
    void testCreateBookingNull() {

        assertFalse(voucherService.create(null));
    }

    @Test
    void testCreate() {

        BookingEntity bookingEntity = BookingEntity.builder()
                .durationInNights(2l)
                .startDate(LocalDateTime.now().toLocalDate())
                .build();
        VoucherEntity voucherEntity = VoucherEntity.builder()
                .name("voucher-test")
                .booking(bookingEntity)
                .hasExpired(false)
                .build();

        when(bookingService.getBooking(any())).thenReturn(bookingEntity);
        when(voucherRepository.save(voucherEntity)).thenReturn(voucherEntity);

        boolean res = voucherService.create(mapper.map(bookingEntity, BookingDto.class));

        assertTrue(res);

    }




}
