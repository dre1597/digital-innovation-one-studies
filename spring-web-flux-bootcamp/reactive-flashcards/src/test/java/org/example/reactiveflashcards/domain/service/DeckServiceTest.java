package org.example.reactiveflashcards.domain.service;

import org.bson.types.ObjectId;
import org.example.reactiveflashcards.core.factorybot.document.DeckDocumentFactoryBot;
import org.example.reactiveflashcards.core.factorybot.dto.DeckDTOFactoryBot;
import org.example.reactiveflashcards.domain.document.DeckDocument;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.mapper.DeckDomainMapper;
import org.example.reactiveflashcards.domain.mapper.DeckDomainMapperImpl;
import org.example.reactiveflashcards.domain.repository.DeckRepository;
import org.example.reactiveflashcards.domain.service.query.DeckQueryService;
import org.example.reactiveflashcards.domain.service.query.DeckRestQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

  private final DeckDomainMapper deckDomainMapper = new DeckDomainMapperImpl();
  @Mock
  private DeckRepository deckRepository;
  @Mock
  private DeckQueryService deckQueryService;
  @Mock
  private DeckRestQueryService deckRestQueryService;
  private DeckService deckService;

  @BeforeEach
  void setup() {
    deckService = new DeckService(deckRepository, deckQueryService, deckRestQueryService, deckDomainMapper);
  }

  @Test
  void saveTest() {
    var document = DeckDocumentFactoryBot.builder().preInsert().build();
    when(deckRepository.save(any())).thenAnswer(invocation -> {
      var deck = invocation.getArgument(0, DeckDocument.class);
      return Mono.just(deck.toBuilder()
          .id(ObjectId.get().toString())
          .createdAt(OffsetDateTime.now())
          .updatedAt(OffsetDateTime.now())
          .build());
    });
    StepVerifier.create(deckService.save(document))
        .assertNext(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual).hasNoNullFieldsOrProperties();
        })
        .verifyComplete();
    verify(deckRepository).save(any());
    verifyNoInteractions(deckQueryService);
    verifyNoInteractions(deckRestQueryService);
  }

  @Test
  void updateTest() {
    var storedDeck = DeckDocumentFactoryBot.builder().build();
    var document = DeckDocumentFactoryBot.builder()
        .preUpdate(storedDeck.id())
        .build();
    when(deckRepository.save(any())).thenAnswer(invocation -> {
      var deck = invocation.getArgument(0, DeckDocument.class);
      return Mono.just(deck.toBuilder().updatedAt(OffsetDateTime.now()).build());
    });
    when(deckQueryService.findById(anyString())).thenReturn(Mono.just(storedDeck));

    StepVerifier.create(deckService.update(document))
        .assertNext(actual -> {
          assertThat(actual).isNotNull();
          assertThat(actual).usingRecursiveComparison()
              .ignoringFields("createdAt", "updatedAt")
              .isEqualTo(document);
          assertThat(actual.createdAt().toEpochSecond()).isEqualTo(storedDeck.createdAt().toEpochSecond());
        })
        .verifyComplete();
    verify(deckRepository).save(any());
    verify(deckQueryService).findById(anyString());
    verifyNoInteractions(deckRestQueryService);
  }

  @Test
  void whenTryToUpdateNonStoredDeckThenThrowError() {
    var document = DeckDocumentFactoryBot.builder().build();
    when(deckQueryService.findById(anyString())).thenReturn(Mono.error(new NotFoundException("")));
    StepVerifier.create(deckService.update(document))
        .verifyError(NotFoundException.class);
    verify(deckQueryService).findById(anyString());
    verify(deckRepository, times(0)).save(any());
    verifyNoInteractions(deckRestQueryService);
  }

  @Test
  void deleteTest() {
    var deckCaptor = ArgumentCaptor.forClass(DeckDocument.class);
    var storedDeck = DeckDocumentFactoryBot.builder().build();
    when(deckRepository.delete(deckCaptor.capture())).thenReturn(Mono.empty());
    when(deckQueryService.findById(anyString())).thenReturn(Mono.just(storedDeck));

    StepVerifier.create(deckService.delete(ObjectId.get().toString())).verifyComplete();
    var capturedDeck = deckCaptor.getValue();
    assertThat(capturedDeck).usingRecursiveComparison()
        .ignoringFields("createdAt", "updatedAt")
        .isEqualTo(storedDeck);
    assertThat(capturedDeck.createdAt().toEpochSecond()).isEqualTo(storedDeck.createdAt().toEpochSecond());
    assertThat(capturedDeck.updatedAt().toEpochSecond()).isEqualTo(storedDeck.updatedAt().toEpochSecond());
    verify(deckRepository).delete(any(DeckDocument.class));
    verify(deckQueryService).findById(anyString());
    verifyNoInteractions(deckRestQueryService);
  }

  @Test
  void whenTryToDeleteNonStoredDeckThenThrowError() {
    when(deckQueryService.findById(anyString())).thenReturn(Mono.error(new NotFoundException("")));
    StepVerifier.create(deckService.delete(ObjectId.get().toString()))
        .verifyError(NotFoundException.class);
    verify(deckQueryService).findById(anyString());
    verify(deckRepository, times(0)).delete(any(DeckDocument.class));
    verifyNoInteractions(deckRestQueryService);
  }

  @Test
  void syncTest() throws InterruptedException {
    var faker = getFaker();
    var deckCaptor = ArgumentCaptor.forClass(DeckDocument.class);
    var externalDecks = Stream.generate(() -> DeckDTOFactoryBot.builder().build())
        .limit(faker.number().randomDigitNotZero()).toList();
    when(deckRestQueryService.getDecks()).thenReturn(Flux.fromIterable(externalDecks));
    when(deckRepository.save(deckCaptor.capture())).thenAnswer(invocation -> {
      var deck = invocation.getArgument(0, DeckDocument.class);
      return Mono.just(deck.toBuilder()
          .id(ObjectId.get().toString())
          .createdAt(OffsetDateTime.now())
          .updatedAt(OffsetDateTime.now())
          .build());
    });

    StepVerifier.create(deckService.sync()).verifyComplete();
    TimeUnit.SECONDS.sleep(2);

    assertThat(deckCaptor.getAllValues().size()).isEqualTo(externalDecks.size());
    verify(deckRepository, times(externalDecks.size())).save(any());
    verify(deckRestQueryService).getDecks();
    verifyNoInteractions(deckQueryService);
  }

}
