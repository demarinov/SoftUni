package com.nlt.app.model.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, CharSequence> {

    private Set<CharSequence> uniqueSet;

    @Override
    public void initialize(UniqueField constraintAnnotation) {

        uniqueSet = new HashSet<>();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {

        if (uniqueSet.contains(charSequence)) {
            return false;
        }

        uniqueSet.add(charSequence);

        return true;
    }
}
