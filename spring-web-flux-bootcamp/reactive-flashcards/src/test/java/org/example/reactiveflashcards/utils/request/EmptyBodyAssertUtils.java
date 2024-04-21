package org.example.reactiveflashcards.utils.request;

import org.springframework.test.web.reactive.server.EntityExchangeResult;

public class EmptyBodyAssertUtils extends AbstractBodyAssertUtils<Void> {

  public EmptyBodyAssertUtils(final EntityExchangeResult<Void> response) {
    super(response);
  }

}
