package com.example.advquerying.services;

import com.example.advquerying.entities.Label;

import java.util.List;

public interface LabelService {

    List<Label> getAllLabelsById(Long id);
}
