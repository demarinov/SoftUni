package com.dido.exam.repository;

import com.dido.exam.model.entity.StyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {

    Optional<StyleEntity> findByName(String name);
}
