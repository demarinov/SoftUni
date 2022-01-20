package com.example.advquerying.repositories;

import com.example.advquerying.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> getAllById(Long id);
}
