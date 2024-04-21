package org.example.reactiveflashcards.domain.helper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.core.RetryConfig;
import org.example.reactiveflashcards.domain.exception.RetryException;
import org.springframework.stereotype.Component;
import reactor.util.retry.Retry;

import java.util.function.Predicate;

import static org.example.reactiveflashcards.domain.exception.BaseErrorMessage.GENERIC_MAX_RETRIES;


@AllArgsConstructor
@Component
@Slf4j
public class RetryHelper {
  private final RetryConfig retryConfig;

  public Retry processRetry(final String retryIdentifier, final Predicate<? super Throwable> errorFilter) {
    return Retry.backoff(retryConfig.maxRetries(), retryConfig.minDurationSeconds())
        .filter(errorFilter)
        .doBeforeRetry(retrySignal -> log.warn("==== Retrying {} - {} times(s)", retryIdentifier,
            retrySignal.totalRetries()))
        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
            new RetryException(GENERIC_MAX_RETRIES.getMessage(), retrySignal.failure()));
  }
}
