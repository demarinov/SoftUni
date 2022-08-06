package com.dido.holidaybay.service;

import com.dido.holidaybay.model.entity.BankAccount;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.repository.BankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankingService {

    private final BankingRepository bankingRepository;

    public boolean deposit(double amount, UserEntity user) {

        if (amount > 0) {

            BankAccount bankAccount = bankingRepository.findByUserId(user.getId()).orElse(null);

            if (bankAccount == null) {

                return false;
            }

            bankAccount.setAmount(bankAccount.getAmount()+amount);

            bankingRepository.save(bankAccount);

            return true;
        }

        return false;
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

            return true;
        }

        return false;
    }

    public BankAccount getBankAccount(UserEntity user) {

        return bankingRepository.findByUserId(user.getId()).orElse(null);
    }
}
