package com.dido.battleships.configuration.converter;

import com.dido.battleships.model.dto.CategoryDto;
import com.dido.battleships.model.enums.ShipEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToShipDtoConverter implements Converter<String, CategoryDto> {
    @Override
    public CategoryDto convert(String source) {

        try {

            return CategoryDto.builder()
                    .name(ShipEnum.valueOf(source))
                    .build();
        } catch (IllegalArgumentException e) {

            return null;
        }
    }
}
