package com.dido.holidaybay.repository;

import com.dido.holidaybay.model.entity.AttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<AttractionEntity, Long> {

}
