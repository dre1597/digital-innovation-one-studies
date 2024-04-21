package org.example.reactiveflashcards.api.controller.deck;

import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.DeckController;
import org.example.reactiveflashcards.api.mapper.DeckMapperImpl;
import org.example.reactiveflashcards.domain.service.DeckService;
import org.example.reactiveflashcards.domain.service.query.DeckQueryService;
import org.example.reactiveflashcards.utils.request.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import static org.example.reactiveflashcards.utils.request.RequestBuilder.noContentRequestBuilder;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DeckMapperImpl.class})
@WebFluxTest(DeckController.class)
class DeckControllerSyncTest extends AbstractControllerTest {

  @MockBean
  public DeckService deckService;
  @MockBean
  public DeckQueryService deckQueryService;
  private RequestBuilder<Void> noContentRequestBuilder;

  @BeforeEach
  void setup() {
    noContentRequestBuilder = noContentRequestBuilder(applicationContext, "/decks");
  }

  @Test
  void syncTest() {
    when(deckService.sync()).thenReturn(Mono.empty());
    noContentRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("sync")
            .build())
        .generateRequestWithoutBody()
        .doPost()
        .httpStatusIsOk();
  }

}
