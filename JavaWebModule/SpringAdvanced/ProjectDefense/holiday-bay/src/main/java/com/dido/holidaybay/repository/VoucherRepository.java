package com.dido.holidaybay.repository;

import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.VoucherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

    Page<VoucherEntity> findByHasExpired(boolean hasExpired, Pageable pageable);
    Page<VoucherEntity> findByHasExpiredAndBookingUser(boolean hasExpired, UserEntity user, Pageable pageable);
}
