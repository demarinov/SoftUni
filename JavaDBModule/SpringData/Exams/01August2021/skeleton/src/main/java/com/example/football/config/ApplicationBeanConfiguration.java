package com.example.football.config;

import com.example.football.util.ValidationUtils;
import com.example.football.util.ValidationUtilsImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                setPrettyPrinting().create();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> dateConverter = new Converter<String, LocalDate>(){
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {

                return LocalDate.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        };

        modelMapper.addConverter(dateConverter);

        return modelMapper;
    }

    @Bean
    public ValidationUtils validationUtils() {

        return new ValidationUtilsImpl();
    }
}
