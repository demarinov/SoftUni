package com.dido.holidaybay.scheduler;

import com.dido.holidaybay.event.BonusDepositEvent;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.entity.BankAccount;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.BankingService;
import com.dido.holidaybay.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BonusDepositScheduler {

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    // on every 10 bookings get 100 $ cash
    @Scheduled(cron = "*/30 * * * * *")
    public void depositScheduler() {
        log.info("depositScheduler(): Scheduling event triggered");

        List<UserEntity> users = userService.getAllUsers();
        BonusDepositEvent bonusDepositEvent = new BonusDepositEvent(BonusDepositScheduler.class.getSimpleName(),
                100d);

        for (UserEntity user : users) {

            if (user.getBookings() != null && user.isBonusEligible()) {
                log.debug("User {} bookings: {}",user.getId(),user.getBookings().size());
                if (user.getBookings().stream().anyMatch(booking ->
                        (booking.getPrice() * booking.getDurationInNights()) >= 2000
                                && booking.isActive())) {

                    bonusDepositEvent.getUserIds().add(user.getId());
                }
            }

        }

        applicationEventPublisher.publishEvent(bonusDepositEvent);

    }
}
