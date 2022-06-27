package com.mobile.configuration.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.models.dtos.ModelDto;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;

public class StringToModelDtoConverter implements Converter<String, ModelDto> {

    @SneakyThrows
    @Override
    public ModelDto convert(String source) {
        ModelDto modelDto = new ModelDto();
        modelDto.setName(source);
        return modelDto;
    }
}
