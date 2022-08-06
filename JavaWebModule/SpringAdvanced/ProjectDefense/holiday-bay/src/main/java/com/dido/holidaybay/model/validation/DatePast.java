package com.dido.holidaybay.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DatePastValidator.class)
public @interface DatePast {


        String message() default "Date should not be in the past";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
