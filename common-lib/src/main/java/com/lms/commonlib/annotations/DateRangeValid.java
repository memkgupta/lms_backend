package com.lms.commonlib.annotations;

import com.lms.commonlib.utils.DateRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateRangeValidator.class) // Link to the validator logic
@Target({ ElementType.TYPE }) // Apply to the whole class
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeValid {
    String message() default "End date must be after start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}