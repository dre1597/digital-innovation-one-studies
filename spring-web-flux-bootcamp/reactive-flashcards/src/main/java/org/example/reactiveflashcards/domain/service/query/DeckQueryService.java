package org.example.reactiveflashcards.domain.service.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.domain.document.DeckDocument;
import org.example.reactiveflashcards.domain.exception.NotFoundException;
import org.example.reactiveflashcards.domain.repository.DeckRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.example.reactiveflashcards.domain.exception.BaseErrorMessage.DECK_NOT_FOUND;


@Service
@Slf4j
@AllArgsConstructor
public class DeckQueryService {
  private final DeckRepository deckRepository;

  public Mono<DeckDocument> findById(final String id) {
    return deckRepository.findById(id)
        .doFirst(() -> log.info("==== try to find deck with id {}", id))
        .filter(Objects::nonNull)
        .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException(DECK_NOT_FOUND.params(id).getMessage()))));
  }

  public Flux<DeckDocument> findAll() {
    return deckRepository.findAll()
        .doFirst(() -> log.info("==== Try to get all decks"));
  }
}
