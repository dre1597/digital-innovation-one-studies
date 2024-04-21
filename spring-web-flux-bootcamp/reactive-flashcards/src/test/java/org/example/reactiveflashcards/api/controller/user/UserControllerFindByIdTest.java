package org.example.reactiveflashcards.api.controller.user;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.AbstractControllerTest;
import org.example.reactiveflashcards.api.controller.UserController;
import org.example.reactiveflashcards.api.controller.response.ProblemResponse;
import org.example.reactiveflashcards.api.controller.response.UserResponse;
import org.example.reactiveflashcards.api.mapper.UserMapperImpl;
import org.example.reactiveflashcards.core.factorybot.document.UserDocumentFactoryBot;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.service.UserService;
import org.example.reactiveflashcards.domain.service.query.UserQueryService;
import org.example.reactiveflashcards.utils.request.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.problemResponseRequestBuilder;
import static org.example.reactiveflashcards.utils.request.RequestBuilder.userResponseRequestBuilder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ContextConfiguration(classes = {UserMapperImpl.class})
@WebFluxTest(UserController.class)
class UserControllerFindByIdTest extends AbstractControllerTest {

  @MockBean
  private UserService userService;
  @MockBean
  private UserQueryService userQueryService;
  private RequestBuilder<UserResponse> userResponseRequestBuilder;
  private RequestBuilder<ProblemResponse> problemResponseRequestBuilder;

  @BeforeEach
  void setup() {
    userResponseRequestBuilder = userResponseRequestBuilder(applicationContext, "/users");
    problemResponseRequestBuilder = problemResponseRequestBuilder(applicationContext, "/users");
  }

  @Test
  void findByIdTest() {
    var user = UserDocumentFactoryBot.builder().build();
    when(userQueryService.findById(anyString())).thenReturn(Mono.just(user));
    userResponseRequestBuilder
        .uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(user.id()))
        .generateRequestWithSimpleBody()
        .doGet()
        .httpStatusIsOk()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response).usingRecursiveComparison()
              .ignoringFields("createdAt", "updatedAt")
              .isEqualTo(user);
        });
  }


  @Test
  void whenTryToFindNonStoredUserThenReturnNotFound() {
    when(userQueryService.findById(anyString())).thenReturn(Mono.error(new NotFoundException("")));
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(ObjectId.get().toString()))
        .generateRequestWithSimpleBody()
        .doGet()
        .httpStatusIsNotFound()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response.status()).isEqualTo(NOT_FOUND.value());
        });
  }

  @Test
  void whenTryUseNonValidIdThenReturnBadRequest() {
    problemResponseRequestBuilder.uri(uriBuilder -> uriBuilder
            .pathSegment("{id}")
            .build(faker.lorem().word()))
        .generateRequestWithSimpleBody()
        .doGet()
        .httpStatusIsBadRequest()
        .assertBody(response -> {
          assertThat(response).isNotNull();
          assertThat(response.status()).isEqualTo(BAD_REQUEST.value());
        });
  }

}
