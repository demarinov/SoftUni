package com.mobile.repositories;

import com.mobile.models.entities.ModelEntity;
import com.mobile.models.entities.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, UUID> {

    List<OfferEntity> getAllBy();
    List<OfferEntity> getAllByModel(ModelEntity model);
}
