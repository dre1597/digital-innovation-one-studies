package org.example.reactiveflashcards.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.api.controller.documentation.DeckControllerDoc;
import org.example.reactiveflashcards.api.controller.request.DeckRequest;
import org.example.reactiveflashcards.api.controller.response.DeckResponse;
import org.example.reactiveflashcards.api.mapper.DeckMapper;
import org.example.reactiveflashcards.core.validation.MongoId;
import org.example.reactiveflashcards.domain.service.DeckService;
import org.example.reactiveflashcards.domain.service.query.DeckQueryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("decks")
@Slf4j
@AllArgsConstructor
public class DeckController implements DeckControllerDoc {

  public final DeckService deckService;
  public final DeckQueryService deckQueryService;
  public final DeckMapper deckMapper;

  @Override
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public Mono<DeckResponse> save(@Valid @RequestBody final DeckRequest request) {
    return deckService.save(deckMapper.toDocument(request))
        .doFirst(() -> log.info("==== Saving a deck with follow data {}", request))
        .map(deckMapper::toResponse);
  }

  @Override
  @PostMapping(value = "sync")
  public Mono<Void> sync() {
    return deckService.sync();
  }

  @Override
  @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}")
  public Mono<DeckResponse> findById(@PathVariable @Valid @MongoId(message = "{deckController.id}") final String id) {
    return deckQueryService.findById(id)
        .doFirst(() -> log.info("==== Finding a deck with follow id {}", id))
        .map(deckMapper::toResponse);
  }

  @Override
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public Flux<DeckResponse> findAll() {
    return deckQueryService.findAll()
        .doFirst(() -> log.info("==== Finding all decks"))
        .map(deckMapper::toResponse);
  }

  @Override
  @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{id}")
  public Mono<DeckResponse> update(@PathVariable @Valid @MongoId(message = "{deckController.id}") final String id,
                                   @Valid @RequestBody final DeckRequest request) {
    return deckService.update(deckMapper.toDocument(request, id))
        .doFirst(() -> log.info("==== Updating a deck with follow info [body: {}, id: {}]", request, id))
        .map(deckMapper::toResponse);
  }


  @Override
  @DeleteMapping(value = "{id}")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> delete(@PathVariable @Valid @MongoId(message = "{deckController.id}") final String id) {
    return deckService.delete(id)
        .doFirst(() -> log.info("==== Deleting a user with follow id {}", id));
  }

}
