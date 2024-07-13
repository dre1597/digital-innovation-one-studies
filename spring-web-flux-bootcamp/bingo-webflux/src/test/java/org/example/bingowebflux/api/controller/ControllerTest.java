package org.example.bingowebflux.api.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

public abstract class ControllerTest {
  private static Validator validator;
  @Autowired
  public WebTestClient client;

  @BeforeAll
  public static void init() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  protected Set<ConstraintViolation<Object>> violation(final Object request) {
    return validator.validate(request);
  }
}
