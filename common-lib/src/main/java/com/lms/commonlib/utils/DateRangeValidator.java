package com.lms.commonlib.utils;

import com.lms.commonlib.annotations.DateRangeValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

// The validator for our @DateRangeValid annotation
public class DateRangeValidator implements ConstraintValidator<DateRangeValid, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value instanceof DateRangeContainer dto) {
            LocalDate startDate = dto.getStartDate();
            LocalDate endDate = dto.getEndDate();

            // Allow null values; use @NotNull on fields if they are required
            if (startDate == null || endDate == null) {
                return true;
            }

            return endDate.isAfter(startDate);
        }
        return false;
    }
}