package com.dido.holidaybay.scheduler;

import com.dido.holidaybay.model.dto.VoucherDto;
import com.dido.holidaybay.service.VoucherService;
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
public class VoucherSchedulerTests {

    @Mock
    private VoucherService voucherService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private VoucherScheduler voucherScheduler;

    @Test
    void voucherSchedulingOk() {

        VoucherDto voucherDto = VoucherDto.builder()
                .hasExpired(true)
                .id(1l)
                .name("voucher-test")
                .build();

        when(voucherService.getExpiredVouchers()).thenReturn(Collections.singletonList(voucherDto));
        when(voucherService.deactivateVoucher(any())).thenReturn(true);

        voucherScheduler.voucherScheduler();

        verify(voucherService).getExpiredVouchers();
        verify(voucherService).deactivateVoucher(any());
    }
}
