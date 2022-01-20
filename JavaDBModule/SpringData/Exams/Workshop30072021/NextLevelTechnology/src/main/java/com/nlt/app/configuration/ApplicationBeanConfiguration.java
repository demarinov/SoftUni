package com.nlt.app.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nlt.app.utils.ValidationUtils;
import com.nlt.app.utils.ValidationUtilsImpl;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidationUtils validationUtil() {
        return new ValidationUtilsImpl();
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> dateConverter = new Converter<String, LocalDate>(){
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {

                return LocalDate.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        };

        modelMapper.addConverter(dateConverter);

        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {


                return LocalDateTime.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        });

        return modelMapper;

    }

}
