package org.example.reactiveflashcards.domain.service.query;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.core.factorybot.document.DeckDocumentFactoryBot;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.repository.DeckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DeckQueryServiceTest {

  @Mock
  private DeckRepository deckRepository;

  private DeckQueryService deckQueryService;

  @BeforeEach
  void setup() {
    deckQueryService = new DeckQueryService(deckRepository);
  }

  @Test
  void findAllTest() {
    var faker = getFaker();
    var documents = Stream.generate(() -> DeckDocumentFactoryBot.builder().build())
        .limit(faker.number().randomDigitNotZero())
        .toList();
    when(deckRepository.findAll()).thenReturn(Flux.fromIterable(documents));
    StepVerifier.create(deckQueryService.findAll())
        .recordWith(ArrayList::new)
        .thenConsumeWhile(actual -> true)
        .consumeRecordedWith(actual -> assertThat(actual).hasSameSizeAs(documents))
        .verifyComplete();
    verify(deckRepository).findAll();
  }

  @Test
  void findByIdTest() {
    var document = DeckDocumentFactoryBot.builder().build();
    when(deckRepository.findById(anyString())).thenReturn(Mono.just(document));
    StepVerifier.create(deckQueryService.findById(ObjectId.get().toString()))
        .assertNext(actual -> assertThat(actual).isNotNull())
        .verifyComplete();
    verify(deckRepository).findById(anyString());
  }

  @Test
  void whenTryToFindNonStoredDeckThenThrowError() {
    when(deckRepository.findById(anyString())).thenReturn(Mono.empty());
    StepVerifier.create(deckQueryService.findById(ObjectId.get().toString())).verifyError(NotFoundException.class);
    verify(deckRepository).findById(anyString());
  }

}
