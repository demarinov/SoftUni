package com.dido.holidaybay.repository;

import com.dido.holidaybay.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankingRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByUserId(Long id);
}
