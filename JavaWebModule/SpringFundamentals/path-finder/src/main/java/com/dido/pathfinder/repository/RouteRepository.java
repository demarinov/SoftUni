package com.dido.pathfinder.repository;

import com.dido.pathfinder.model.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

    @Override
    List<RouteEntity> findAll();
}
