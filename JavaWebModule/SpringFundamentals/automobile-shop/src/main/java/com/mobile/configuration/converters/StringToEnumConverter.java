package com.mobile.configuration.converters;


import com.mobile.models.enums.EngineEnum;
import org.springframework.core.convert.converter.Converter;


public class StringToEnumConverter implements Converter<String, EngineEnum> {

    @Override
    public EngineEnum convert(String from) {
        String[] split = from.split("\\s");
        return EngineEnum.valueOf(split[0].toUpperCase());
    }
}
