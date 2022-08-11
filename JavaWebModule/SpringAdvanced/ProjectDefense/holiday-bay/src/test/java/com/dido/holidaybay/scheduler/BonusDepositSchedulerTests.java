package com.dido.holidaybay.scheduler;

import com.dido.holidaybay.event.BonusDepositEvent;
import com.dido.holidaybay.model.entity.BookingEntity;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BonusDepositSchedulerTests {

    @Mock
    private UserService userService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private BonusDepositScheduler bonusDepositScheduler;


    @Test
    void testDepositScheduler() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .bonusEligible(true)
                .bookings(Collections.singletonList(BookingEntity.builder()
                        .price(1000d)
                        .durationInNights(3L)
                        .active(true)
                        .build()))
                .build();
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
        BonusDepositEvent bonusDepositEvent =
                new BonusDepositEvent(BonusDepositSchedulerTests.class.getSimpleName(),
                100d);

        bonusDepositScheduler.depositScheduler();
        verify(applicationEventPublisher).publishEvent(any());
    }
}
