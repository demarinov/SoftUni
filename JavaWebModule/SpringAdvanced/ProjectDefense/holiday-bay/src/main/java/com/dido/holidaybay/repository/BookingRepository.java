package com.dido.holidaybay.repository;

import com.dido.holidaybay.model.entity.BookingEntity;
import com.dido.holidaybay.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    Page<BookingEntity> findByActiveAndUserId(boolean b, Long id, Pageable pageable);
}
