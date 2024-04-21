package org.example.reactiveflashcards.api.controller.study;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.StudyController;
import org.example.reactiveflashcards.api.controller.request.AnswerQuestionRequest;
import org.example.reactiveflashcards.api.controller.response.AnswerQuestionResponse;
import org.example.reactiveflashcards.api.controller.response.ErrorFieldResponse;
import org.example.reactiveflashcards.api.controller.response.ProblemResponse;
import org.example.reactiveflashcards.api.mapper.StudyMapperImpl;
import org.example.reactiveflashcards.core.factorybot.document.DeckDocumentFactoryBot;
import org.example.reactiveflashcards.core.factorybot.document.StudyDocumentFactoryBot;
import org.example.reactiveflashcards.core.factorybot.request.AnswerQuestionRequestFactoryBot;
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
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.answerQuestionResponseRequestBuilder;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.problemResponseRequestBuilder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ContextConfiguration(classes = {StudyMapperImpl.class})
@WebFluxTest(StudyController.class)
class StudyControllerAnswerTest extends AbstractControllerTest {

  @MockBean
  private StudyService studyService;
  @MockBean
  private StudyQueryService studyQueryService;

  private RequestBuilder<AnswerQuestionResponse> answerQuestionResponseRequestBuilder;
  private RequestBuilder<ProblemResponse> problemResponseRequestBuilder;

  private static Stream<Arguments> checkConstraintTest() {
    return Stream.of(
        Arguments.of(AnswerQuestionRequestFactoryBot.builder().build(),
            faker.lorem().word(),
            "id"),
        Arguments.of(AnswerQuestionRequestFactoryBot.builder().blankAnswer().build(),
            ObjectId.get().toString(),
            "answer"),
        Arguments.of(AnswerQuestionRequestFactoryBot.builder().longAnswer().build(),
            ObjectId.get().toString(),
            "answer")
    );
  }

  @BeforeEach
  void setup() {
    answerQuestionResponseRequestBuilder = answerQuestionResponseRequestBuilder(applicationContext, "/studies");
    problemResponseRequestBuilder = problemResponseRequestBuilder(applicationContext, "/studies");
  }

  @Test
  void answerTest() {
    var deck = DeckDocumentFactoryBot.builder().build();
    var study = StudyDocumentFactoryBot.builder(ObjectId.get().toString(), deck)
        .pendingQuestions(1)
        .build();
    var request = AnswerQuestionRequestFactoryBot.builder().build();
    when(studyService.answer(anyString(), anyString())).thenReturn(Mono.just(study));
    answerQuestionResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .pathSegment("answer")
            .build(study.id()))
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsOk()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          var currentQuestion = study.getLastAnsweredQuestion();
          assertThat(response.answered()).isEqualTo(currentQuestion.answered());
          assertThat(response.answeredIn().toEpochSecond()).isEqualTo(currentQuestion.answeredIn().toEpochSecond());
          assertThat(response.asked()).isEqualTo(currentQuestion.asked());
          assertThat(response.askedIn().toEpochSecond()).isEqualTo(currentQuestion.askedIn().toEpochSecond());
          assertThat(response.expected()).isEqualTo(currentQuestion.expected());
        });
  }

  @Test
  void whenNotFoundStudyOrStudyIsFinishedThenReturnNotFound() {
    var request = AnswerQuestionRequestFactoryBot.builder().build();
    when(studyService.answer(anyString(), anyString())).thenReturn(Mono.error(new NotFoundException("")));
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .pathSegment("answer")
            .build(ObjectId.get().toString()))
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
  void checkConstraintTest(final AnswerQuestionRequest request, final String studyId,
                           final String field) {
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .pathSegment("answer")
            .build(studyId))
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsBadRequest()
        .assertBody(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.status()).isEqualTo(BAD_REQUEST.value());
          assertThat(actual.fields().stream().map(ErrorFieldResponse::name).toList()).contains(field);
        });
  }

}
