package org.example.bingowebflux.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bson.types.ObjectId;

public class ObjectIdIsValidImpl implements ConstraintValidator<ObjectIdIsValid, String> {
  @Override
  public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
    return ObjectId.isValid(id);
  }
}
