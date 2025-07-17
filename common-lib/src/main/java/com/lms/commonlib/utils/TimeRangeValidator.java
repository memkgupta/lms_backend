package com.lms.commonlib.utils;

import com.lms.commonlib.annotations.TimeRangeValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeValid,TimeRangeContainer> {
    @Override
    public boolean isValid(TimeRangeContainer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.getStart() == null || value.getEnd() == null)
            return true;
        return value.getStart().isBefore(value.getEnd());
    }
}
