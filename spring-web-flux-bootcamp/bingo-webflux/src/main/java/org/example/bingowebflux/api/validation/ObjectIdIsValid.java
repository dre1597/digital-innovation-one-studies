package org.example.bingowebflux.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.example.bingowebflux.domain.constant.ErrorMessages.GENERIC_INVALID_OBJECT_ID;

@Documented
@Constraint(validatedBy = ObjectIdIsValidImpl.class)
@Retention(RUNTIME)
@Target(PARAMETER)
@ReportAsSingleViolation
public @interface ObjectIdIsValid {
  String message() default GENERIC_INVALID_OBJECT_ID;

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
