package app.model.validators.password;

import app.constants.ValidationConstraints;
import app.constants.ValidationMessages;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default ValidationMessages.PASSWORD_CANNOT_BE_EMPTY;

    int minLength() default ValidationConstraints.USER_PASSWORD_MIN_LENGTH;
    int maxLength() default ValidationConstraints.USER_PASSWORD_MAX_LENGTH;

    boolean containsUpperCaseLetter() default ValidationConstraints.USER_PASSWORD_SHOULD_CONTAIN_UPPER_CASE;
    boolean containsLowerCaseLetter() default ValidationConstraints.USER_PASSWORD_SHOULD_CONTAIN_LOWER_CASE;
    boolean containsDigit() default ValidationConstraints.USER_PASSWORD_SHOULD_CONTAIN_DIGIT;

    boolean nullable() default ValidationConstraints.USER_PASSWORD_CAN_BE_OMITTED;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
