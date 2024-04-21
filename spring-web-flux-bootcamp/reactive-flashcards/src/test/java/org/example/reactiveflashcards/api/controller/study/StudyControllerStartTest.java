package org.example.reactiveflashcards.api.controller.study;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.StudyController;
import org.example.reactiveflashcards.api.controller.request.StudyRequest;
import org.example.reactiveflashcards.api.controller.response.ErrorFieldResponse;
import org.example.reactiveflashcards.api.controller.response.ProblemResponse;
import org.example.reactiveflashcards.api.controller.response.QuestionResponse;
import org.example.reactiveflashcards.api.mapper.StudyMapperImpl;
import org.example.reactiveflashcards.core.factorybot.document.DeckDocumentFactoryBot;
import org.example.reactiveflashcards.core.factorybot.document.StudyDocumentFactoryBot;
import org.example.reactiveflashcards.core.factorybot.request.StudyRequestFactoryBot;
import org.example.reactiveflashcards.domain.document.StudyDocument;
import org.example.reactiveflashcards.domain.exception.DeckInStudyException;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.service.StudyService;
import org.example.reactiveflashcards.domain.service.query.StudyQueryService;
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

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.problemResponseRequestBuilder;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.questionResponseRequestBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ContextConfiguration(classes = {StudyMapperImpl.class})
@WebFluxTest(StudyController.class)
class StudyControllerStartTest extends AbstractControllerTest {

  @MockBean
  private StudyService studyService;
  @MockBean
  private StudyQueryService studyQueryService;

  private RequestBuilder<QuestionResponse> questionResponseRequestBuilder;
  private RequestBuilder<ProblemResponse> problemResponseRequestBuilder;

  private static Stream<Arguments> checkConstraintsTest() {
    return Stream.of(
        Arguments.of(StudyRequestFactoryBot.builder().invalidDeckId().build(), "deckId"),
        Arguments.of(StudyRequestFactoryBot.builder().invalidUserId().build(), "userId")
    );
  }

  @BeforeEach
  void setup() {
    questionResponseRequestBuilder = questionResponseRequestBuilder(applicationContext, "/studies");
    problemResponseRequestBuilder = problemResponseRequestBuilder(applicationContext, "/studies");
  }

  @Test
  void startTest() {
    var deck = DeckDocumentFactoryBot.builder().build();
    var study = StudyDocumentFactoryBot.builder(ObjectId.get().toString(), deck)
        .pendingQuestions(1)
        .build();
    var request = StudyRequestFactoryBot.builder().build();
    when(studyService.start(any(StudyDocument.class))).thenReturn(Mono.just(study));
    questionResponseRequestBuilder.uri(UriBuilder::build)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsCreated()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response.id()).isEqualTo(study.id());
          assertThat(response.asked()).isEqualTo(study.getLastPendingQuestion().asked());
          assertThat(response.askedIn()).isEqualTo(study.getLastPendingQuestion().askedIn());
        });
  }

  @Test
  void whenHasOtherStudyWithSameDeckForUserThenReturnConflict() {
    when(studyService.start(any(StudyDocument.class))).thenReturn(Mono.error(new DeckInStudyException("")));
    var request = StudyRequestFactoryBot.builder().build();
    problemResponseRequestBuilder.uri(UriBuilder::build)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsConflict()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(CONFLICT.value());
        });
  }

  @Test
  void whenTryToStartStudyWithoutStoredDeckOrUSerThenReturnNotFound() {
    when(studyService.start(any(StudyDocument.class))).thenReturn(Mono.error(new NotFoundException("")));
    var request = StudyRequestFactoryBot.builder().build();
    problemResponseRequestBuilder.uri(UriBuilder::build)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsNotFound()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(NOT_FOUND.value());
        });
  }

  @ParameterizedTest
  @MethodSource
  void checkConstraintsTest(final StudyRequest request, final String errorField) {
    problemResponseRequestBuilder.uri(UriBuilder::build)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsBadRequest()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(BAD_REQUEST.value());
          assertThat(actual.fields().stream().map(ErrorFieldResponse::name).toList()).contains(errorField);
        });
  }

}
