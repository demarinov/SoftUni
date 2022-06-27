package com.dido.exam.configuration;

import com.dido.exam.model.entity.StyleEntity;
import com.dido.exam.model.enums.StyleEnum;
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
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        Converter<StyleEntity, StyleEnum> styleConverter = new AbstractConverter<StyleEntity, StyleEnum>() {
            protected StyleEnum convert(StyleEntity source) {
                return source == null ? null : StyleEnum.valueOf(source.getName());
            }
        };

        mapper.addConverter(styleConverter);
        return mapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

}
