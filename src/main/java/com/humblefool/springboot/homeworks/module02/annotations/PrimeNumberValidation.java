package com.humblefool.springboot.homeworks.module02.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PrimeNumberValidator.class)
public @interface PrimeNumberValidation {
    String message() default "This Number must be a prime number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
