package org.example.reactiveflashcards.domain.exception;

public class RetryException extends ReactiveFlashcardsException {
  public RetryException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
