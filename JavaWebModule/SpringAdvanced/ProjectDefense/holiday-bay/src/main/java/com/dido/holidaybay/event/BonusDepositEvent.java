package com.dido.holidaybay.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BonusDepositEvent extends ApplicationEvent {

    private List<Long> userIds;
    private double bonus;

    public BonusDepositEvent(Object source, double bonus) {
        super(source);
        this.userIds = new ArrayList<>();
        this.bonus = bonus;
    }
}
