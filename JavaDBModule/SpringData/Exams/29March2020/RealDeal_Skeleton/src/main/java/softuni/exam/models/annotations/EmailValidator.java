package softuni.exam.models.annotations;

import softuni.exam.util.AnnotationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private Pattern pattern;
    private boolean nullable;

    @Override
    public void initialize(Email constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
        this.pattern = Pattern.compile(constraintAnnotation.regex());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {

        if (charSequence == null) {
            return nullable;
        }

        if (!pattern.matcher(charSequence).matches()) {
            AnnotationUtils.setErrorMessage(constraintValidatorContext, "invalid email");
            return false;
        }

        return true;
    }
}
