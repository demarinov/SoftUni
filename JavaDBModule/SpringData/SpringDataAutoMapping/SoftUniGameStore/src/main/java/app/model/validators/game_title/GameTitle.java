package app.model.validators.game_title;

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
@Constraint(validatedBy = GameTitleValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameTitle {

    String message() default ValidationMessages.GAME_TITLE_CANNOT_BE_EMPTY;

    int minLength() default ValidationConstraints.GAME_TITLE_MIN_LENGTH;
    int maxLength() default ValidationConstraints.GAME_TITLE_MAX_LENGTH;

    boolean capitalFirstLetter() default ValidationConstraints.GAME_TITLE_UPPER_FIRST_LETTER;

    boolean nullable() default ValidationConstraints.GAME_TITLE_CAN_BE_OMITTED;


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
