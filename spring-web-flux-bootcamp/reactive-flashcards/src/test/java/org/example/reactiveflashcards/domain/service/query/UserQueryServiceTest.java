package org.example.reactiveflashcards.domain.service.query;

import com.github.javafaker.Faker;
import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.request.UserPageRequest;
import org.example.reactiveflashcards.core.factorybot.document.UserDocumentFactoryBot;
import org.example.reactiveflashcards.core.factorybot.request.UserPageRequestFactoryBot;
import org.example.reactiveflashcards.domain.document.UserDocument;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.repository.UserRepository;
import org.example.reactiveflashcards.domain.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

  private final static Faker faker = getFaker();
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserRepositoryImpl userRepositoryImpl;
  private UserQueryService userQueryService;

  private static Stream<Arguments> findOnDemandTest() {
    var documents = Stream.generate(() -> UserDocumentFactoryBot.builder().build())
        .limit(faker.number().randomDigitNotZero())
        .toList();
    var pageRequest = UserPageRequestFactoryBot.builder().build();
    var total = faker.number().numberBetween(documents.size(), documents.size() * 3L);
    return Stream.of(
        Arguments.of(documents,
            total,
            pageRequest,
            (total / pageRequest.limit()) + ((total % pageRequest.limit() > 0) ? 1 : 0)
        ),
        Arguments.of(new ArrayList<>(), 0L, UserPageRequestFactoryBot.builder().build(), 0L)
    );
  }

  @BeforeEach
  void setup() {
    userQueryService = new UserQueryService(userRepository, userRepositoryImpl);
  }

  @Test
  void findByIdTest() {
    var document = UserDocumentFactoryBot.builder().build();
    when(userRepository.findById(anyString())).thenReturn(Mono.just(document));

    StepVerifier.create(userQueryService.findById(ObjectId.get().toString()))
        .assertNext(actual -> assertThat(actual).usingRecursiveComparison()
            .ignoringFields("createdAt", "updatedAt")
            .isEqualTo(document))
        .verifyComplete();
    verify(userRepository).findById(anyString());
    verifyNoInteractions(userRepositoryImpl);
  }

  @Test
  void whenTryToFindNonStoredUserByIdThenThrowError() {
    when(userRepository.findById(anyString())).thenReturn(Mono.empty());

    StepVerifier.create(userQueryService.findById(ObjectId.get().toString()))
        .verifyError(NotFoundException.class);
    verify(userRepository).findById(anyString());
    verifyNoInteractions(userRepositoryImpl);
  }

  @Test
  void findByEmailTest() {
    var document = UserDocumentFactoryBot.builder().build();
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(document));

    StepVerifier.create(userQueryService.findByEmail(faker.internet().emailAddress()))
        .assertNext(actual -> assertThat(actual).usingRecursiveComparison()
            .ignoringFields("createdAt", "updatedAt")
            .isEqualTo(document))
        .verifyComplete();
    verify(userRepository).findByEmail(anyString());
    verifyNoInteractions(userRepositoryImpl);
  }

  @Test
  void whenTryToFindNonStoredUserByEmailThenThrowError() {
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.empty());

    StepVerifier.create(userQueryService.findByEmail(faker.internet().emailAddress()))
        .verifyError(NotFoundException.class);
    verify(userRepository).findByEmail(anyString());
    verifyNoInteractions(userRepositoryImpl);
  }

  @MethodSource
  @ParameterizedTest
  void findOnDemandTest(final List<UserDocument> documents, final Long total,
                        final UserPageRequest pageRequest, final Long expectTotalPages) {
    when(userRepositoryImpl.findOnDemand(any(UserPageRequest.class))).thenReturn(Flux.fromIterable(documents));
    when(userRepositoryImpl.count(any(UserPageRequest.class))).thenReturn(Mono.just(total));

    StepVerifier.create(userQueryService.findOnDemand(pageRequest))
        .assertNext(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual.content()).containsExactlyInAnyOrderElementsOf(documents);
          assertThat(actual.totalItems()).isEqualTo(total);
          assertThat(actual.totalPages()).isEqualTo(expectTotalPages);
        })
        .verifyComplete();
  }

}
