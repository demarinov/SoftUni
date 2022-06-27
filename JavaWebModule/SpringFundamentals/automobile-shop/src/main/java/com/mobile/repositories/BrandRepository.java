package com.mobile.repositories;

import com.mobile.models.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> getAllBy();
    List<BrandEntity> getAllByName(String name);
}
