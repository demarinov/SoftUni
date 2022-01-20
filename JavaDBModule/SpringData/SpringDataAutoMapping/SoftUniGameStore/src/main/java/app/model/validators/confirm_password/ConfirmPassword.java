package app.model.validators.confirm_password;

import app.constants.ValidationMessages;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {

    String message() default ValidationMessages.PASSWORD_CONFIRM_INVALID;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
