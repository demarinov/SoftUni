package com.mobile.services;

import com.mobile.models.dtos.ModelDto;
import com.mobile.models.entities.ModelEntity;
import com.mobile.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ModelDto> getAllModels() {

        return modelRepository.getAllBy().stream()
                .map(model -> modelMapper.map(model, ModelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getAllModelsByBrand() {
        return getAllModelsByBrand().stream()
                .map(model -> modelMapper.map(model, ModelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createModel(ModelDto modelDto) {
        ModelEntity model = modelMapper.map(modelDto, ModelEntity.class);
        modelRepository.save(model);
    }

    @Override
    public ModelDto findModel(Long id) {
        return modelMapper.map(modelRepository.findById(id).orElse(null), ModelDto.class);
    }


}
