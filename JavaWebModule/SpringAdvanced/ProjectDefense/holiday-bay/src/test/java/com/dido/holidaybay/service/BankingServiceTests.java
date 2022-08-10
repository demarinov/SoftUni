package com.dido.holidaybay.service;

import com.dido.holidaybay.event.BonusDepositEvent;
import com.dido.holidaybay.model.entity.BankAccount;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.repository.BankingRepository;
import com.dido.holidaybay.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankingServiceTests {

    @Autowired
    private BankingService bankingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankingRepository bankingRepository;

    private UserEntity user;

    private BankAccount newAccount;

    @BeforeEach
    void init() {
        user = UserEntity.builder()
                .userName("budi@mail.com")
                .id(99l)
                .firstName("budi")
                .lastName("bob")
                .build();

        newAccount = BankAccount.builder()
                .user(user)
                .amount(10000d)
                .build();

        user = userRepository.save(user);

        newAccount.setUser(user);

        newAccount = bankingRepository.save(newAccount);
        user.setBankAccount(newAccount);
    }

    @AfterEach
    void end() {
        bankingRepository.delete(user.getBankAccount());
        userRepository.delete(user);
    }

    @Test
    void testGetBankAccount() {
        BankAccount bankAccount = bankingService.getBankAccount(user);
        assertEquals(newAccount.getId(),bankAccount.getId());
    }


    @Test
    void depositOk() {
        assertTrue(bankingService.deposit(10, user));
        double bankAmountCurrent = user.getBankAccount().getAmount()-10;
        assertEquals(bankAmountCurrent+10, user.getBankAccount().getAmount());
    }

    @Test
    void depositNegative() {
        assertFalse(bankingService.deposit(-10, user));
    }

    @Test
    void depositNoBankAccount() {
        BankAccount bankAccount = user.getBankAccount();
        bankAccount.setUser(null);
        bankingRepository.save(bankAccount);
        assertFalse(bankingService.deposit(10, user));
    }

    @Test
    void bonusDepositOk() {
        BonusDepositEvent bonusDepositEvent =
                new BonusDepositEvent(BankingServiceTests.class.getSimpleName(), 100d);

        bonusDepositEvent.setUserIds(Arrays.asList(user.getId()));
        assertTrue(bankingService.depositBonus(bonusDepositEvent));
        UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);

        double bankAmountCurrent = userEntity.getBankAccount().getAmount()-100d;
        assertEquals(bankAmountCurrent+100, userEntity.getBankAccount().getAmount());
    }

    @Test
    void withdrawOk() {
        assertTrue(bankingService.withdraw(10, user));
        double bankAmountCurrent = user.getBankAccount().getAmount()+10;
        assertEquals(bankAmountCurrent-10, user.getBankAccount().getAmount());
    }

    @Test
    void withdrawNotEnoughFunds() {
        assertFalse(bankingService.withdraw(2000000, user));
    }

    @Test
    void withdrawNegative() {
        assertFalse(bankingService.withdraw(-10, user));
    }

    @Test
    void withdrawNoBankAccount() {
        BankAccount bankAccount = user.getBankAccount();
        bankAccount.setUser(null);
        bankingRepository.save(bankAccount);
        assertFalse(bankingService.withdraw(10, user));
    }

    @Test
    void testGetBankAccounts() {
        long expectedCount = bankingRepository.findAll().size();

        assertEquals(expectedCount, bankingService.getBankAccounts().size());
    }


}
