package com.dido.holidaybay.service;

import com.dido.holidaybay.client.RoomClient;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.RoomDto;
import com.dido.holidaybay.model.entity.BookingEntity;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BankingService bankingService;
    private final RoomClient roomClient;

    public boolean addBooking(BookingDto bookingDto, UserEntity user) {

        if (bookingDto != null) {

            BookingEntity bookingEntity = BookingEntity.builder()
                    .active(true)
                    .durationInNights(bookingDto.getDurationInNights())
                    .roomId(bookingDto.getRoomId())
                    .price(bookingDto.getPrice())
                    .user(user)
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .startDate(bookingDto.getStartDate())
                    .build();

            double amount = bookingDto.getPrice() * bookingDto.getDurationInNights();

            bookingRepository.save(bookingEntity);

            boolean result = bankingService.withdraw(amount, user);

            if (!result) {
                bookingRepository.delete(bookingEntity);
                return false;
            }

            roomClient.updateRoomStatus(bookingDto.getRoomId(), false);
            bookingDto.setId(bookingEntity.getId());
            return true;
        }

        return false;
    }

    public List<BookingDto> getUserBookings(UserEntity user) {

        return bookingRepository.findAll().stream().filter(booking -> booking.isActive()
                        && booking.getUser().getId().equals(user.getId()))
                .map(this::map).collect(Collectors.toList());
    }

    public Page<BookingDto> getUserBookings(UserEntity user, Pageable pageable) {

        return bookingRepository.findByActiveAndUserId(true, user.getId(),pageable)
                .map(this::map);
    }

    private BookingDto map(BookingEntity bookingEntity) {
        RoomDto roomDto = roomClient.getRoomById(bookingEntity.getRoomId());

        return BookingDto.builder()
                .id(bookingEntity.getId())
                .roomId(bookingEntity.getRoomId())
                .imageUrl(roomDto.getImageUrl())
                .durationInNights(bookingEntity.getDurationInNights())
                .price(bookingEntity.getPrice())
                .active(bookingEntity.isActive())
                .startDate(bookingEntity.getStartDate())
                .build();
    }

    public BookingEntity getBooking(Long id) {

        return bookingRepository.findById(id).orElse(null);
    }

    public boolean updateBooking(BookingEntity booking) {

        if (booking != null) {

            bookingRepository.save(booking);
            return true;
        }

        return false;
    }

    public List<BookingDto> getUserBookingsHistory(UserEntity user) {
        return bookingRepository.findAll().stream().filter(booking -> !booking.isActive()
                        && booking.getUser().getId().equals(user.getId()))
                .map(this::map).collect(Collectors.toList());
    }

    public Page<BookingDto> getUserBookingsHistory(UserEntity user, Pageable pageable) {
        return bookingRepository.findByActiveAndUserId(false, user.getId(),pageable)
                .map(this::map);
    }
}
