package org.example.bingowebflux.api.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends BingoException{
  public NotFoundException(String message) {
    super(message, NOT_FOUND);
  }
}

