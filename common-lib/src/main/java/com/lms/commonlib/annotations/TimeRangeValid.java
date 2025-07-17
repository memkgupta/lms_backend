package com.lms.commonlib.annotations;

import com.lms.commonlib.utils.TimeRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeRangeValidator.class)
@Target({ ElementType.TYPE }) // Apply to the whole class
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeRangeValid {
    String message() default "End time must be after start time";

}
