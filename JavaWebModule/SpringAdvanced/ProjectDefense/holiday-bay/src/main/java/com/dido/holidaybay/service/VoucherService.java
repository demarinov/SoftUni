package com.dido.holidaybay.service;

import com.dido.holidaybay.client.RoomClient;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.VoucherDto;
import com.dido.holidaybay.model.entity.BookingEntity;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.VoucherEntity;
import com.dido.holidaybay.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final BookingService bookingService;
    private final ModelMapper mapper;
    private final RoomClient roomClient;

    private static final String VOUCHER_NAME_PREFIX = "Holiday-Bay-";

    public List<VoucherDto> getVouchers() {
        return voucherRepository.findAll().stream().filter(voucher -> !isVoucherExpired(voucher))
                .map(voucherEntity -> mapper.map(voucherEntity, VoucherDto.class))
                .collect(Collectors.toList());
    }

    public List<VoucherDto> getVouchers(UserEntity user) {
        return voucherRepository.findAll().stream()
                .filter(voucher -> !isVoucherExpired(voucher)
                        && voucher.getBooking().getUser().equals(user))
                .map(voucherEntity -> mapper.map(voucherEntity, VoucherDto.class))
                .collect(Collectors.toList());
    }

    public Page<VoucherDto> getVouchers(UserEntity user, Pageable pageable) {

        return voucherRepository.findByHasExpiredAndBookingUser(false, user,pageable)
                .map(voucherEntity -> mapper.map(voucherEntity, VoucherDto.class));
    }

    public boolean create(BookingDto bookingDto) {

        if (bookingDto != null) {
            BookingEntity bookingEntity = bookingService.getBooking(bookingDto.getId());
            if (bookingEntity == null) {
                return false;
            }

            LocalDateTime created = LocalDateTime.now();
            LocalDateTime endDate = calculateEndDate(bookingEntity.getStartDate().atStartOfDay(), bookingDto);

            VoucherEntity voucherEntity = VoucherEntity
                    .builder()
                    .booking(bookingEntity)
                    .name(String.format("%s%d", VOUCHER_NAME_PREFIX, bookingDto.getRoomId()))
                    .created(created)
                    .modified(created)
                    .imageUrl("https://media.istockphoto.com/photos/3d-render-of-hotel-entrance-and-reception-picture-id908258590?k=20&m=908258590&s=612x612&w=0&h=yoQWtVKqfgb2Nhza5bEOh8oHn1LqU4b53LNasRjZQiA=")
                    .endDate(endDate)
                    .hasExpired(false)
                    .build();

            voucherRepository.save(voucherEntity);

            return true;
        }

        return false;
    }

    private LocalDateTime calculateEndDate(LocalDateTime created, BookingDto bookingDto) {

        return created.plusDays(bookingDto.getDurationInNights());
    }

    public boolean isVoucherExpired(VoucherEntity voucherEntity) {
        return voucherEntity.getEndDate() == null || LocalDateTime.now().isAfter(voucherEntity.getEndDate());
    }

    public boolean deactivateVoucher(VoucherDto voucherDto) {

        VoucherEntity voucher = voucherRepository.findById(voucherDto.getId()).orElse(null);

        if (voucher != null) {
            roomClient.updateRoomStatus(voucher.getBooking().getRoomId(), true);
            voucher.getBooking().setActive(false);
            bookingService.updateBooking(voucher.getBooking());
            voucher.setHasExpired(true);
            voucher.setEndDate(null);
            voucherRepository.save(voucher);



            return true;
        }

        return false;
    }
}
