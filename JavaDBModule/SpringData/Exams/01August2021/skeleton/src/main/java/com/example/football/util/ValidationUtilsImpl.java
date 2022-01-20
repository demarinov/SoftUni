package com.example.football.util;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUtilsImpl implements ValidationUtils{

    private Validator validator;

    @Override
    public <T> boolean isValid(T entity) {

        validator = Validation.buildDefaultValidatorFactory().getValidator();

        return validator.validate(entity).isEmpty();
    }
}
