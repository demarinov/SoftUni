package com.dido.holidaybay.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookingExpirationEvent extends ApplicationEvent {

    private LocalDateTime expiredDate;
    private List<Long> bookingIds;

    public BookingExpirationEvent(Object source, LocalDateTime expiredDate) {
        super(source);
        this.expiredDate = expiredDate;
        this.bookingIds = new ArrayList<>();
    }
}
