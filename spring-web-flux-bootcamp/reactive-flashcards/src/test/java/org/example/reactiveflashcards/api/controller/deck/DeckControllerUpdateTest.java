package org.example.reactiveflashcards.api.controller.deck;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.DeckController;
import org.example.reactiveflashcards.api.controller.request.DeckRequest;
import org.example.reactiveflashcards.api.controller.response.DeckResponse;
import org.example.reactiveflashcards.api.controller.response.ErrorFieldResponse;
import org.example.reactiveflashcards.api.controller.response.ProblemResponse;
import org.example.reactiveflashcards.api.mapper.DeckMapperImpl;
import org.example.reactiveflashcards.core.factorybot.request.DeckRequestFactoryBot;
import org.example.reactiveflashcards.domain.document.DeckDocument;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.service.DeckService;
import org.example.reactiveflashcards.domain.service.query.DeckQueryService;
import org.example.reactiveflashcards.utils.request.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.deckResponseRequestBuilder;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.problemResponseRequestBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ContextConfiguration(classes = {DeckMapperImpl.class})
@WebFluxTest(DeckController.class)
class DeckControllerUpdateTest extends AbstractControllerTest {

  @MockBean
  public DeckService deckService;
  @MockBean
  public DeckQueryService deckQueryService;
  private RequestBuilder<DeckResponse> deckResponseRequestBuilder;
  private RequestBuilder<ProblemResponse> problemResponseRequestBuilder;

  private static Stream<Arguments> checkConstraintsTest() {
    Function<UriBuilder, URI> validId = uriBuilder -> uriBuilder
        .pathSegment("{id}")
        .build(ObjectId.get().toString());
    Function<UriBuilder, URI> invalidId = uriBuilder -> uriBuilder
        .pathSegment("{id}")
        .build(faker.lorem().word());
    return Stream.of(
        Arguments.of(DeckRequestFactoryBot.builder().blankName().build(), "name", validId),
        Arguments.of(DeckRequestFactoryBot.builder().longName().build(), "name", validId),
        Arguments.of(DeckRequestFactoryBot.builder().blankDescription().build(), "description", validId),
        Arguments.of(DeckRequestFactoryBot.builder().longDescription().build(), "description", validId),
        Arguments.of(DeckRequestFactoryBot.builder().nullCards().build(), "cards", validId),
        Arguments.of(DeckRequestFactoryBot.builder().lessThanThreeCards().build(), "cards", validId),
        Arguments.of(DeckRequestFactoryBot.builder().cardWithBlankFront().build(), "cards[].front", validId),
        Arguments.of(DeckRequestFactoryBot.builder().cardWithLongFront().build(), "cards[].front", validId),
        Arguments.of(DeckRequestFactoryBot.builder().cardWithBlankBack().build(), "cards[].back", validId),
        Arguments.of(DeckRequestFactoryBot.builder().cardWithLongBack().build(), "cards[].back", validId),
        Arguments.of(DeckRequestFactoryBot.builder().build(), "id", invalidId)
    );
  }

  @BeforeEach
  void setup() {
    deckResponseRequestBuilder = deckResponseRequestBuilder(applicationContext, "/decks");
    problemResponseRequestBuilder = problemResponseRequestBuilder(applicationContext, "/decks");
  }

  @Test
  void updateTest() {
    when(deckService.update(any(DeckDocument.class))).thenAnswer(invocation -> {
      var document = invocation.getArgument(0, DeckDocument.class);
      return Mono.just(document.toBuilder().updatedAt(OffsetDateTime.now()).build());
    });
    var request = DeckRequestFactoryBot.builder().build();
    deckResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(ObjectId.get().toString()))
        .body(request)
        .generateRequestWithSimpleBody()
        .doPut()
        .httpStatusIsOk()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response).usingRecursiveComparison()
              .ignoringFields("id")
              .isEqualTo(request);
        });
  }

  @Test
  void whenTryToUpdateNonStoredDeckThenReturnNotFound() {
    when(deckService.update(any(DeckDocument.class))).thenReturn(Mono.error(new NotFoundException("")));
    var request = DeckRequestFactoryBot.builder().build();
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(ObjectId.get().toString()))
        .body(request)
        .generateRequestWithSimpleBody()
        .doPut()
        .httpStatusIsNotFound()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response.status()).isEqualTo(NOT_FOUND.value());
        });
  }

  @ParameterizedTest
  @MethodSource
  void checkConstraintsTest(final DeckRequest request, final String field, final Function<UriBuilder, URI> uriFunction) {
    problemResponseRequestBuilder.uri(uriFunction)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPut()
        .httpStatusIsBadRequest()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(BAD_REQUEST.value());
          assertThat(actual.fields().stream().map(ErrorFieldResponse::name).toList()).contains(field);
        });
  }

}
