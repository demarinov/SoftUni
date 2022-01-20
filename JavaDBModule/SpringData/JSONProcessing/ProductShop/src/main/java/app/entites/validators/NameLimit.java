package app.entites.validators;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = NameLimitValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NameLimit {

    String message() default ValidationMessages.NAME_SHOULD_NOT_BE_EMPTY;

    int minLength() default -1;

    int maxLength() default -1;

    boolean nullable() default ValidationConstraints.NAME_CAN_BE_NULL;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
