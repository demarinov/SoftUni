package softuni.exam.util;


import org.springframework.stereotype.Component;

import javax.validation.Validator;
import javax.validation.Validation;

@Component
public class ValidationUtilImpl implements ValidationUtil{
    @Override
    public <E> boolean isValid(E entity) {

        // validates based on annotation constraints
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        return validator.validate(entity).isEmpty();
    }
}
