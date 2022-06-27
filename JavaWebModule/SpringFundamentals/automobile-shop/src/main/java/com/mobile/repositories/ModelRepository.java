package com.mobile.repositories;

import com.mobile.models.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    List<ModelEntity> getAllBy();
    List<ModelEntity> getAllByName(String name);
}
