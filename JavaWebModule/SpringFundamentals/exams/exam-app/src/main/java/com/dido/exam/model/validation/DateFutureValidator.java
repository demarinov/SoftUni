package com.dido.exam.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateFutureValidator implements ConstraintValidator<DateFuture, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

        LocalDate today = LocalDate.now();
        return !date.isAfter(today);
    }
}
