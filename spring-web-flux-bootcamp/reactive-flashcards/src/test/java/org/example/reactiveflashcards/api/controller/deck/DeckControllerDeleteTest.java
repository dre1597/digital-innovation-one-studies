package org.example.reactiveflashcards.api.controller.deck;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.DeckController;
import org.example.reactiveflashcards.api.controller.response.ErrorFieldResponse;
import org.example.reactiveflashcards.api.controller.response.ProblemResponse;
import org.example.reactiveflashcards.api.mapper.DeckMapperImpl;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.service.DeckService;
import org.example.reactiveflashcards.domain.service.query.DeckQueryService;
import org.example.reactiveflashcards.utils.request.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.noContentRequestBuilder;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.problemResponseRequestBuilder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ContextConfiguration(classes = {DeckMapperImpl.class})
@WebFluxTest(DeckController.class)
final class DeckControllerDeleteTest extends AbstractControllerTest {

  @MockBean
  public DeckService deckService;
  @MockBean
  public DeckQueryService deckQueryService;
  private RequestBuilder<Void> noContentRequestBuilder;
  private RequestBuilder<ProblemResponse> problemResponseRequestBuilder;

  @BeforeEach
  void setup() {
    noContentRequestBuilder = noContentRequestBuilder(applicationContext, "/decks");
    problemResponseRequestBuilder = problemResponseRequestBuilder(applicationContext, "/decks");
  }

  @Test
  void deleteTest() {
    when(deckService.delete(anyString())).thenReturn(Mono.empty());
    noContentRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(ObjectId.get().toString()))
        .generateRequestWithSimpleBody()
        .doDelete()
        .httpStatusIsNoContent();
  }

  @Test
  void whenTryToDeleteNonStoredDeckThenReturnNotFound() {
    when(deckService.delete(anyString())).thenReturn(Mono.error(new NotFoundException("")));
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(ObjectId.get().toString()))
        .generateRequestWithSimpleBody()
        .doDelete()
        .httpStatusIsNotFound()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(NOT_FOUND.value());
        });
  }

  @Test
  void whenTryUseInvalidIdThenReturnBadRequest() {
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(faker.lorem().word()))
        .generateRequestWithSimpleBody()
        .doDelete()
        .httpStatusIsBadRequest()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(BAD_REQUEST.value());
          assertThat(actual.fields().stream().map(ErrorFieldResponse::name).toList()).contains("id");
        });
  }

}
