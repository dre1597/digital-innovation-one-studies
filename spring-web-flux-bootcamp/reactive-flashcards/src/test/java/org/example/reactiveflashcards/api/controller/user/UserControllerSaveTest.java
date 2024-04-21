package org.example.reactiveflashcards.api.controller.user;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.UserController;
import org.example.reactiveflashcards.api.controller.request.UserRequest;
import org.example.reactiveflashcards.api.controller.response.ErrorFieldResponse;
import org.example.reactiveflashcards.api.controller.response.ProblemResponse;
import org.example.reactiveflashcards.api.controller.response.UserResponse;
import org.example.reactiveflashcards.api.mapper.UserMapperImpl;
import org.example.reactiveflashcards.core.factorybot.request.UserRequestFactoryBot;
import org.example.reactiveflashcards.domain.document.UserDocument;
import org.example.reactiveflashcards.domain.exception.EmailAlreadyUsedException;
import org.example.reactiveflashcards.domain.service.UserService;
import org.example.reactiveflashcards.domain.service.query.UserQueryService;
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

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.problemResponseRequestBuilder;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.userResponseRequestBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ContextConfiguration(classes = {UserMapperImpl.class})
@WebFluxTest(UserController.class)
class UserControllerSaveTest extends AbstractControllerTest {

  @MockBean
  private UserService userService;
  @MockBean
  private UserQueryService userQueryService;
  private RequestBuilder<UserResponse> userResponseRequestBuilder;
  private RequestBuilder<ProblemResponse> problemResponseRequestBuilder;

  private static Stream<Arguments> checkConstraintsTest() {
    return Stream.of(
        Arguments.of(UserRequestFactoryBot.builder().blankName().build(), "name"),
        Arguments.of(UserRequestFactoryBot.builder().longName().build(), "name"),
        Arguments.of(UserRequestFactoryBot.builder().blankEmail().build(), "email"),
        Arguments.of(UserRequestFactoryBot.builder().longEmail().build(), "email"),
        Arguments.of(UserRequestFactoryBot.builder().invalidEmail().build(), "email")
    );
  }

  @BeforeEach
  void setup() {
    userResponseRequestBuilder = userResponseRequestBuilder(applicationContext, "/users");
    problemResponseRequestBuilder = problemResponseRequestBuilder(applicationContext, "/users");
  }

  @Test
  void saveTest() {
    when(userService.save(any(UserDocument.class))).thenAnswer(invocation -> {
      var document = invocation.getArgument(0, UserDocument.class);
      return Mono.just(document.toBuilder()
          .id(ObjectId.get().toString())
          .createdAt(OffsetDateTime.now())
          .updatedAt(OffsetDateTime.now())
          .build());
    });
    var request = UserRequestFactoryBot.builder().build();
    userResponseRequestBuilder.uri(UriBuilder::build)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsCreated()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response.id()).isNotNull();
          assertThat(response).usingRecursiveComparison()
              .ignoringFields("id")
              .isEqualTo(request);
        });
  }

  @Test
  void whenTryUseEmailInUseThenReturnConflict() {
    when(userService.save(any(UserDocument.class))).thenReturn(Mono.error(new EmailAlreadyUsedException("")));
    var request = UserRequestFactoryBot.builder().build();
    problemResponseRequestBuilder.uri(UriBuilder::build)
        .body(request)
        .generateRequestWithSimpleBody()
        .doPost()
        .httpStatusIsBadRequest()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response.status()).isEqualTo(BAD_REQUEST.value());
        });
  }

  @ParameterizedTest
  @MethodSource
  void checkConstraintsTest(final UserRequest request, final String field) {
    problemResponseRequestBuilder.uri(UriBuilder::build)
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
