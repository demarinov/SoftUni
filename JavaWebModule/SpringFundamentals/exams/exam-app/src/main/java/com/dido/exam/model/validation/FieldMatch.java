package com.dido.exam.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = com.dido.exam.model.validation.FieldMatchValidator.class)
public @interface FieldMatch {

    String first();
    String second();

    String message() default "Invalid userName.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
