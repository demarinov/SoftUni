package app.entites.validators;

import javax.validation.ConstraintValidatorContext;

public class AnnotationUtils {

    private AnnotationUtils() {

    }

    public static void setErrorMessage(final ConstraintValidatorContext context,final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
