package com.dido.holidaybay.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DatePastValidator implements ConstraintValidator<DatePast, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

        if (date == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }
}
