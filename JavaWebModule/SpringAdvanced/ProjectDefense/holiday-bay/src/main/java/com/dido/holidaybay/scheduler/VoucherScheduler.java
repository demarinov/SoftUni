package com.dido.holidaybay.scheduler;

import com.dido.holidaybay.model.dto.VoucherDto;
import com.dido.holidaybay.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class VoucherScheduler {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final VoucherService voucherService;

    @Scheduled(cron = "*/30 * * * * *")
    public void voucherScheduler() {
        log.info("voucherScheduler(): Scheduling event triggered");

        List<VoucherDto> expiredVouchers = voucherService.getExpiredVouchers();

        for (VoucherDto expiredVoucher : expiredVouchers) {
            voucherService.deactivateVoucher(expiredVoucher);
        }

    }
}
