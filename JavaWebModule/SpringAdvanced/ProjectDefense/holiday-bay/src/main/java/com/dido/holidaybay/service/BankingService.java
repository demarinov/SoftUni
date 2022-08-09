package com.dido.holidaybay.service;

import com.dido.holidaybay.event.BonusDepositEvent;
import com.dido.holidaybay.model.entity.BankAccount;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.repository.BankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankingService {

    private final BankingRepository bankingRepository;
    private final UserService userService;

    public boolean deposit(double amount, UserEntity user) {

        if (amount > 0) {

            BankAccount bankAccount = bankingRepository.findByUserId(user.getId()).orElse(null);

            if (bankAccount == null) {

                return false;
            }

            bankAccount.setAmount(bankAccount.getAmount()+amount);

            bankingRepository.save(bankAccount);
            user.setBankAccount(bankAccount);

            return true;
        }

        return false;
    }

    @EventListener(BonusDepositEvent.class)
    public boolean depositBonus(BonusDepositEvent event) {

        for (Long userId : event.getUserIds()) {
            UserEntity userEntity = userService.getUserById(userId);
            if (userEntity != null) {
                deposit(event.getBonus(), userEntity);
                userEntity.setBonusEligible(false);
                userService.updateUser(userEntity);
            }
        }


        return !event.getUserIds().isEmpty();
    }

    public boolean withdraw(double amount, UserEntity user) {
        if (amount > 0) {

            BankAccount bankAccount = bankingRepository.findByUserId(user.getId()).orElse(null);

            if (bankAccount == null) {

                return false;
            }

            if (bankAccount.getAmount() < amount) {
                // not enough funds
                return false;
            }

            bankAccount.setAmount(bankAccount.getAmount() - amount);

            bankingRepository.save(bankAccount);
            user.setBankAccount(bankAccount);

            return true;
        }

        return false;
    }

    public BankAccount getBankAccount(UserEntity user) {

        return bankingRepository.findByUserId(user.getId()).orElse(null);
    }

    public List<BankAccount> getBankAccounts() {

        return bankingRepository.findAll();
    }
}
