package org.example.bingowebflux.api.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class BusinessException extends BingoException {
  public BusinessException(String message) {
    super(message, UNPROCESSABLE_ENTITY);
  }
}
