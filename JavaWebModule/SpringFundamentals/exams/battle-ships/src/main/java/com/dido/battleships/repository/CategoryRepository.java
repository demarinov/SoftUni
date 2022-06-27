package com.dido.battleships.repository;

import com.dido.battleships.model.entity.CategoryEntity;
import com.dido.battleships.model.enums.ShipEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    Optional<CategoryEntity> findByName(ShipEnum name);
}
