package com.dido.battleships.service;

import com.dido.battleships.model.dto.CategoryDto;
import com.dido.battleships.model.entity.CategoryEntity;
import com.dido.battleships.model.enums.ShipEnum;
import com.dido.battleships.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }


    public boolean addCategory(CategoryDto categoryDto) {
        if (categoryDto != null) {

            categoryRepository.save(mapper.map(categoryDto, CategoryEntity.class));
            return true;
        }

        return false;
    }

    public CategoryEntity findByName(ShipEnum name) {
        return categoryRepository.findByName(name).orElse(null);
    }

}
