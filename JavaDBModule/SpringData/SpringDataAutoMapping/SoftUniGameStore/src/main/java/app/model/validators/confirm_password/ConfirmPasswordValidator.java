package app.model.validators.confirm_password;

import app.constants.ValidationMessages;
import app.model.dtos.RegisterUserDto;
import app.model.validators.ValidatorsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {


    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(final Object value, ConstraintValidatorContext context) {

        if (value instanceof RegisterUserDto) {
            RegisterUserDto registerUserDto = (RegisterUserDto) value;

            if (registerUserDto.getPassword() == null ||
                    !registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
                return false;
            }
        }

        return true;
    }
}
