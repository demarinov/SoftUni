package app.annotations;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    int minLength ();
    int maxLength ();
    boolean containsDigits ();
    boolean containsLowerCase ();
    boolean containsSpecialSymbol ();
    String errorMessage () default "Error";

}
