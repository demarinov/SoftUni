package com.dido.pathfinder.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UniqueUserNameValidator.class)
public @interface UniqueUserName {

    String field();

    String secondaryField();

    String message() default "Username is already occupied";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
