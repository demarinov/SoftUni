package com.dido.exam.service;

import com.dido.exam.model.dto.StyleDto;
import com.dido.exam.model.entity.StyleEntity;
import com.dido.exam.model.enums.StyleEnum;
import com.dido.exam.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StyleService {

    private final StyleRepository styleRepository;
    private final ModelMapper mapper;

    public List<StyleDto> getAll() {

        return styleRepository.findAll().stream()
                .map(category -> mapper.map(category, StyleDto.class))
                .collect(Collectors.toList());
    }

    public boolean addStyle(StyleDto styleDto) {
        if (styleDto != null) {

            styleRepository.save(mapper.map(styleDto, StyleEntity.class));
            return true;
        }

        return false;
    }

    public StyleEntity findByName(String name) {
        return styleRepository.findByName(name).orElse(null);
    }

}
