package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Label;
import com.example.advquerying.repositories.LabelRepository;
import com.example.advquerying.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    private LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public List<Label> getAllLabelsById(Long id) {
        return labelRepository.getAllById(id);
    }
}
