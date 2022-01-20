package app.model.validators.password;

import app.constants.ValidationMessages;
import app.model.validators.ValidatorsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password,CharSequence> {

    private static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");

    private int minLength;
    private int maxLength;
    private boolean containsUpperCaseLetter;
    private boolean containsLowerCaseLetter;
    private boolean containsDigit;
    private boolean nullable;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.containsLowerCaseLetter = constraintAnnotation.containsLowerCaseLetter();
        this.containsUpperCaseLetter = constraintAnnotation.containsUpperCaseLetter();
        this.containsDigit = constraintAnnotation.containsDigit();
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return nullable;
        }

        if (value.length() < minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.USER_NAME_TOO_SHORT);
            return false;
        }

        if (value.length() > maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.USER_NAME_TOO_LONG);
            return false;
        }

        if (containsUpperCaseLetter && !PATTERN_UPPER.matcher(value).find()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER);
            return false;
        }

        if (containsLowerCaseLetter && !PATTERN_LOWER.matcher(value).find()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER);
            return false;
        }

        if (containsDigit && !PATTERN_DIGIT.matcher(value).find()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER);
            return false;
        }

        return true;
    }
}
