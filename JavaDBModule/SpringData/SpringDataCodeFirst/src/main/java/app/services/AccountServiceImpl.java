package app.services;

import app.models.Account;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.repositories.AccountRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        if (accountRepository.existsById(id)) {

            Account account = accountRepository.findAccountById(id);

            User user = account.getUser();

            if (user != null) {
                BigDecimal newBalance = account.getBalance().subtract(money);
                if (newBalance.doubleValue() >= 0) {
                    account.setBalance(newBalance);

                    accountRepository.save(account);
                }
            }
        }

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

        if (accountRepository.existsById(id) && money.doubleValue() > 0) {
            Account account = accountRepository.findAccountById(id);

            User user = account.getUser();

            if (user != null) {
                BigDecimal newBalance = account.getBalance().add(money);
                account.setBalance(newBalance);

                accountRepository.save(account);
            }
        }
    }
}
