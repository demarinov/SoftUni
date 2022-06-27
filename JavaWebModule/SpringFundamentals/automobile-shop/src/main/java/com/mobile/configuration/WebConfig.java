package com.mobile.configuration;

import com.mobile.configuration.converters.StringToEnumConverter;
import com.mobile.configuration.converters.StringToModelDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
        registry.addConverter(new StringToModelDtoConverter());
    }

}
