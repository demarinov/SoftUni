package app.model.validators.email;

import app.constants.ValidationMessages;
import app.model.validators.ValidatorsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private Pattern pattern;
    private boolean nullable;

    @Override
    public void initialize(Email constraintAnnotation) {
       this.pattern = Pattern.compile(constraintAnnotation.mailRegex());
       this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return this.nullable;
        }

        if (!this.pattern.matcher(value).matches()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.EMAIL_INVALID);
            return false;
        }

        return true;
    }
}
