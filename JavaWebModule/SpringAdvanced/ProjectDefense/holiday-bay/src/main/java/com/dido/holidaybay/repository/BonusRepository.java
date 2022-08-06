package com.dido.holidaybay.repository;

import com.dido.holidaybay.model.entity.BonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<BonusEntity, Long> {

    Optional<BonusEntity> findByUserId(Long userId);
    List<BonusEntity> findAllByNotGiven(boolean notGiven);
    Optional<BonusEntity> findByNotGiven(boolean notGiven);
}
