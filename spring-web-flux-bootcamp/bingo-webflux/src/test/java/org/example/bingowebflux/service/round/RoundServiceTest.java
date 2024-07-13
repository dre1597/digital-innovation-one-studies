package org.example.bingowebflux.service.round;

import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;
import org.example.bingowebflux.domain.document.RoundDocument;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.dto.round.RoundCardDTO;
import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.example.bingowebflux.domain.dto.round.RoundPlayerDTO;
import org.example.bingowebflux.domain.executor.DrawExecutor;
import org.example.bingowebflux.domain.executor.GenerateCardExecutor;
import org.example.bingowebflux.domain.mapper.round.RoundMapper;
import org.example.bingowebflux.domain.repository.round.RoundRepository;
import org.example.bingowebflux.domain.validator.GenerateCardValidator;
import org.example.bingowebflux.service.player.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.bingowebflux.domain.constant.ErrorMessages.*;
import static org.example.bingowebflux.domain.constant.Examples.ID_EXAMPLE;
import static org.example.bingowebflux.domain.enumeration.round.RoundStatus.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoundServiceTest {
  @InjectMocks
  private RoundService service;

  @Mock
  private RoundRepository repository;

  @Mock
  private RoundMapper mapper;

  @Mock
  private PlayerService playerService;

  @Mock
  private GenerateCardValidator generateCardValidator;

  @Mock
  private GenerateCardExecutor generateCardExecutor;

  @Mock
  private DrawExecutor drawExecutor;

  @Test
  void shouldGetAllRounds() {
    var pageable = PageRequest.of(0, 1);
    var total = 1L;
    var round = getRound().build();
    var page = new PageImpl<>(List.of(round), pageable, total);

    when(repository.countByStatus(any())).thenReturn(Mono.just(total));
    when(repository.findByStatus(any(), any())).thenReturn(Flux.just(getRoundDocument()));
    when(mapper.toRound(any())).thenReturn(round);

    var result = service.findAll(CREATED, pageable);

    StepVerifier.create(result)
        .expectNext(page)
        .verifyComplete();
  }

  @Test
  void shouldGetARoundById() {
    var round = getRound().build();
    getByIdSuccess(round);

    var result = service.getById(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectNext(round)
        .verifyComplete();
  }

  @Test
  void shouldNotGetARoundByIdWhenNotExists() {
    when(repository.findById(any(ObjectId.class)))
        .thenReturn(Mono.empty());

    var result = service.getById(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectErrorMessage(ROUND_NOT_FOUND)
        .verify();
  }

  @Test
  void shouldCreateARound() {
    var round = getRound().build();
    save(round);
    var result = service.create();

    StepVerifier.create(result)
        .expectNext(round)
        .verifyComplete();
  }

  @Test
  void shouldGetLastNumberDrawnByTheRoundId() {
    var round = getRound()
        .lastDraw(1)
        .status(RUNNING)
        .build();

    getByIdSuccess(round);

    var result = service.getLastNumber(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectNext(round.getLastDraw())
        .verifyComplete();
  }

  @Test
  void shouldNotGetLastNumberDrawnByTheRoundId() {
    var round = getRound()
        .lastDraw(1)
        .status(FINISHED)
        .build();

    getByIdSuccess(round);

    var result = service.getLastNumber(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectErrorMessage(ROUND_LAST_NUMBER_DRAWN_NOT_RUNNING)
        .verify();
  }

  @Test
  void shouldDrawNextNumberByTheRoundId() {
    var round = getRound()
        .cards(List.of(getRoundCard().build(), getRoundCard().build()))
        .build();

    getByIdSuccess(round);
    when(drawExecutor.execute(any()))
        .thenReturn(Mono.just(round));
    save(round);

    var result = service.generateNumber(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectNextMatches(t -> t.getWinners().isEmpty())
        .verifyComplete();
  }

  @Test
  void shouldDrawNextNumberByTheRoundIdWithWinners() {
    var round = getRound()
        .cards(List.of(getRoundCard().build(), getRoundCard().build()))
        .winners(List.of(getRoundCard().build()))
        .build();

    getByIdSuccess(round);

    var result = service.generateNumber(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectNextMatches(t -> !t.getWinners().isEmpty())
        .verifyComplete();
  }

  @Test
  void shouldNotDrawNextNumberByTheRoundId() {
    var round = getRound()
        .cards(List.of(getRoundCard().build()))
        .build();

    getByIdSuccess(round);

    var result = service.generateNumber(ID_EXAMPLE);

    StepVerifier.create(result)
        .expectErrorMessage(ROUND_NUMBER_OF_INVALID_CARDS_FOR_THE_ROUND)
        .verify();
  }

  @Test
  void shouldGenerateCard() {
    var round = getRound().status(CREATED).build();
    var player = getPlayer().build();

    getByIdSuccess(round);
    playerGetById(player);
    when(generateCardValidator.validate(any(), any()))
        .thenCallRealMethod();
    when(generateCardExecutor.execute(any()))
        .thenReturn(Mono.just(Pair.of(round, getRoundCard().build())));
    save(round);

    var result = service.generateCard(ID_EXAMPLE, ID_EXAMPLE);

    StepVerifier.create(result)
        .expectNextMatches(Objects::nonNull)
        .verifyComplete();
  }

  @Test
  void shouldNotGenerateCardWithInvalidCard() {
    var round = getRound().status(RUNNING).build();
    var player = getPlayer().build();

    getByIdSuccess(round);
    playerGetById(player);
    when(generateCardValidator.validate(any(), any()))
        .thenCallRealMethod();

    var result = service.generateCard(ID_EXAMPLE, ID_EXAMPLE);

    StepVerifier.create(result)
        .expectErrorMessage(ROUND_CREATE_CARD_INVALID_STATUS)
        .verify();
  }

  @Test
  void shouldNotGenerateCardWithExistsCard() {
    var player = getPlayer()
        .id(ID_EXAMPLE)
        .build();

    var cards = List.of(getRoundCard()
        .owner(getRoundPlayer())
        .build());

    var round = getRound()
        .status(CREATED)
        .cards(cards)
        .build();

    getByIdSuccess(round);
    playerGetById(player);
    when(generateCardValidator.validate(any(), any()))
        .thenCallRealMethod();

    var result = service.generateCard(ID_EXAMPLE, ID_EXAMPLE);

    StepVerifier.create(result)
        .expectErrorMessage(ROUND_CREATE_CARD_EXISTS_CARD_FOR_PLAYER)
        .verify();
  }

  private RoundPlayerDTO getRoundPlayer() {
    return RoundPlayerDTO.builder()
        .id(ID_EXAMPLE)
        .build();
  }

  private PlayerDTO.PlayerDTOBuilder getPlayer() {
    return PlayerDTO.builder();
  }

  private RoundCardDTO.RoundCardDTOBuilder getRoundCard() {
    return RoundCardDTO.builder()
        .numbers(getElements());
  }

  private List<Integer> getElements() {
    return new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
  }

  private void save(RoundDTO round) {
    when(mapper.toDocument(any())).thenReturn(getRoundDocument());
    when(repository.save(any())).thenReturn(Mono.just(getRoundDocument()));
    when(mapper.toRound(any())).thenReturn(round);
  }

  private void playerGetById(PlayerDTO player) {
    when(playerService.getById(anyString()))
        .thenReturn(Mono.just(player));
  }

  private void getByIdSuccess(RoundDTO round) {
    when(repository.findById(any(ObjectId.class)))
        .thenReturn(Mono.just(getRoundDocument()));
    when(mapper.toRound(any())).thenReturn(round);
  }

  private RoundDocument getRoundDocument() {
    return RoundDocument.builder().build();
  }

  private RoundDTO.RoundDTOBuilder getRound() {
    return RoundDTO.builder();
  }
}
