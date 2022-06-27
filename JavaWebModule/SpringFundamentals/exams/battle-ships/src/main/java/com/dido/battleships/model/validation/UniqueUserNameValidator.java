package com.dido.battleships.model.validation;


import com.dido.battleships.model.dto.UserDto;
import com.dido.battleships.model.entity.UserEntity;
import com.dido.battleships.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, UserDto> {

    private final UserRepository userRepository;
    private String field;
    private String secondaryField;
    private String message;

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.message = constraintAnnotation.message();
        this.secondaryField = constraintAnnotation.secondaryField();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.
                forBeanPropertyAccess(value);

        String fieldValue =(String) beanWrapper.getPropertyValue(this.field);
        UserEntity dbUser = userRepository.findByUsername(fieldValue).orElse(null);

        boolean valid = false;

        if (dbUser == null) {
            valid = true;
        }

        if (!valid) {
            context.
                    buildConstraintViolationWithTemplate(message).
                    addPropertyNode(secondaryField).
                    addConstraintViolation().
                    disableDefaultConstraintViolation();
        }

        return valid;
    }
}
