package com.nlt.app.utils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilsImpl implements ValidationUtils{

    private Validator validator;

    @Override
    public <T> boolean isValid(T entity) {

        validator = Validation.buildDefaultValidatorFactory().getValidator();

        return validator.validate(entity).isEmpty();
    }
}
