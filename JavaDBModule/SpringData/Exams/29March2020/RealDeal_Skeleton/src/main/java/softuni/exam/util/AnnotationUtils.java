package softuni.exam.util;

import javax.validation.ConstraintValidatorContext;

public final class AnnotationUtils {

    private AnnotationUtils() {
    }

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}