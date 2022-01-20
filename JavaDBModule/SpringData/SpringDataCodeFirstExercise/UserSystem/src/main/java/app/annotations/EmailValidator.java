package app.annotations;

import app.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.validation.Validator;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {
    private int minUserNameLength;
    private int maxUserNameLength;
    private int maxHostNameLength;
    private Pattern pattern;

    @Override
    public void initialize(final Email email) {

        this.minUserNameLength = email.minUserNameLength();
        this.maxUserNameLength = email.maxUserNameLength();
        this.maxHostNameLength = email.maxHostNameLength();
        this.pattern = Pattern.compile(email.regex());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        String email = value.toString();
        int userNameLength = email.indexOf("@");
        int hostNameLength = email.length() - email.lastIndexOf("@") - 1;

        if (userNameLength < this.minUserNameLength) {
            AnnotationUtils.setErrorMessage(context, TextConstants.EMAIL_NAME_LENGTH_TOO_SHORT);
            return false;
        }

        if (userNameLength > this.maxUserNameLength) {
            AnnotationUtils.setErrorMessage(context, TextConstants.EMAIL_NAME_LENGTH_TOO_LONG);
            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            AnnotationUtils.setErrorMessage(context, TextConstants.EMAIL_HOST_LENGTH_TOO_LONG);
            return false;
        }

        return this.pattern.matcher(email).matches();
    }
}
