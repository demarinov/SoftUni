package app.annotations;

import app.constants.TextConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private int minLength;
    private int maxLength;
    private boolean containsDigits;
    private boolean containsLowerCase;
    private boolean containsSpecialSymbol;
    private String errorMessage;

    @Override
    public void initialize(Password password) {

        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigits = password.containsDigits();
        this.containsLowerCase = password.containsLowerCase();
        this.containsSpecialSymbol = password.containsSpecialSymbol();
        this.errorMessage = password.errorMessage();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            AnnotationUtils.setErrorMessage(context,
                   this.errorMessage + TextConstants.PASSWORD_CANNOT_BE_NULL);
            return false;
        }

        if (value.length() < this.minLength) {
            AnnotationUtils.setErrorMessage(context,
                    this.errorMessage + TextConstants.PASSWORD_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            AnnotationUtils.setErrorMessage(context,
                    this.errorMessage + TextConstants.PASSWORD_TOO_LONG);
            return false;
        }

        if (this.containsDigits && !containsDigits(value)) {
            AnnotationUtils.setErrorMessage(context,
                    this.errorMessage + TextConstants.PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        if (this.containsDigits && !containsLowerCase(value)) {
            AnnotationUtils.setErrorMessage(context,
                    this.errorMessage + TextConstants.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER);
            return false;
        }

        if (this.containsLowerCase && !containsDigits(value)) {
            AnnotationUtils.setErrorMessage(context,
                    this.errorMessage + TextConstants.PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        if (this.containsSpecialSymbol && !containsSpecialSymbol(value)) {
            AnnotationUtils.setErrorMessage(context,
                    this.errorMessage + TextConstants.PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL);
            return false;
        }

        return true;
    }

    private boolean containsLowerCase(CharSequence value) {
        for (int i = 0; i < value.length(); i++) {
            if (Character.isLowerCase(value.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    private boolean containsDigits(CharSequence value) {
        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    private boolean containsSpecialSymbol(CharSequence value) {

        int countAlphas = 0;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isAlphabetic(value.charAt(i))) {
                countAlphas++;
            }
        }

        return countAlphas == value.length() ? false : true;
    }
}
