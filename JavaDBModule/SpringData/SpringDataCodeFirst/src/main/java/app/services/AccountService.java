package app.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal money, Long id);
}
