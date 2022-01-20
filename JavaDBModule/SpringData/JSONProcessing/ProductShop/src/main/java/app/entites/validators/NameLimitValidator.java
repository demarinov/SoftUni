package app.entites.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameLimitValidator implements ConstraintValidator<NameLimit, CharSequence> {

    private int minLength;
    private int maxLength;
    private boolean nullable;


    @Override
    public void initialize(NameLimit constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return nullable;
        }

        if (value.length() >= 0 && value.length() < minLength) {
            AnnotationUtils.setErrorMessage(context, ValidationMessages.NAME_TOO_SHORT);
            return false;
        }

        if (value.length() >= 0 && value.length() > maxLength) {
            AnnotationUtils.setErrorMessage(context, ValidationMessages.NAME_TOO_LONG);
            return false;
        }

        return true;
    }
}
