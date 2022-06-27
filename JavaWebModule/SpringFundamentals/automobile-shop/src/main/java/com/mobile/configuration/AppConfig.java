package com.mobile.configuration;

import com.mobile.models.dtos.ModelDto;
import com.mobile.models.entities.ModelEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        Converter<ModelEntity, String> modelConverter = new AbstractConverter<ModelEntity, String>() {
            protected String convert(ModelEntity source) {
                return source == null ? null : source.getName();
            }
        };

        Converter<String, ModelDto> modelDtoConverter = new AbstractConverter<String, ModelDto>() {
            protected ModelDto convert(String source) {
                ModelDto modelDto = new ModelDto();
                modelDto.setName(source);
                return source == null ? null : modelDto;
            }
        };

        mapper.addConverter(modelConverter);
        mapper.addConverter(modelDtoConverter);
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new Pbkdf2PasswordEncoder();
    }


}
