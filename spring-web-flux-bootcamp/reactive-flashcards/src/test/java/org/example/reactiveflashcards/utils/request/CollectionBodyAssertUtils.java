package org.example.reactiveflashcards.utils.request;

import org.springframework.test.web.reactive.server.EntityExchangeResult;

import java.util.List;

public class CollectionBodyAssertUtils<B> extends AbstractBodyAssertUtils<List<B>> {

  public CollectionBodyAssertUtils(final EntityExchangeResult<List<B>> response) {
    super(response);
  }
}
