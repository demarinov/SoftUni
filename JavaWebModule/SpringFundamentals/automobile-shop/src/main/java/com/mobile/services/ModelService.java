package com.mobile.services;

import com.mobile.models.dtos.ModelDto;

import java.util.List;

public interface ModelService {

    List<ModelDto> getAllModels();

    List<ModelDto> getAllModelsByBrand();
    void createModel(ModelDto model);

    ModelDto findModel(Long id);
}
