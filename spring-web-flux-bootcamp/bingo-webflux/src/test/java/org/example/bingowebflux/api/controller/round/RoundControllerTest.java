package org.example.bingowebflux.api.controller.round;

import org.apache.commons.lang3.tuple.Pair;
import org.example.bingowebflux.api.controller.ControllerTest;
import org.example.bingowebflux.api.exception.CustomExceptionHandler;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.round.*;
import org.example.bingowebflux.domain.mapper.round.RoundMapper;
import org.example.bingowebflux.service.round.RoundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.example.bingowebflux.domain.constant.Examples.ID_EXAMPLE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {RoundController.class, CustomExceptionHandler.class})
@WebFluxTest(controllers = RoundController.class)
class RoundControllerTest extends ControllerTest {
  private static final String ROUND_URL = "/rounds";
  private static final String ROUND_URL_ID = "/rounds/{id}";
  private static final String ROUND_URL_LAST_NUMBER = "/rounds/{id}/last-numbers";
  private static final String ROUND_URL_GENERATE_NUMBER = "/rounds/{id}/generate-numbers";
  private static final String ROUND_URL_GENERATE_CARD = "/rounds/{id}/generate-card/players/{playerId}";
  @MockBean
  private RoundService service;
  @MockBean
  private RoundMapper mapper;

  @Test
  void shouldGetAllRounds() {
    when(service.findAll(any(), any()))
        .thenReturn(Mono.just(getReturnSuccessList()));
    when(mapper.toPage(any()))
        .thenReturn(getReturnSuccessPageResponse());

    this.client
        .get()
        .uri(ROUND_URL)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldGetRoundById() {
    when(service.getById(anyString()))
        .thenReturn(Mono.just(getRound()));
    when(mapper.toResponse(any()))
        .thenReturn(getResponse());

    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_ID)
        .buildAndExpand(ID_EXAMPLE)
        .toUriString();

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldNotGetRoundByIdWIthInvalidId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_ID)
        .buildAndExpand("xpto")
        .toUriString();

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldNotGetRoundByIdWithEmptyId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_ID)
        .buildAndExpand(" ")
        .toUriString();

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldCreateRound() {
    when(service.create())
        .thenReturn(Mono.just(getRound()));
    when(mapper.toResponse(any()))
        .thenReturn(getResponse());

    this.client
        .post()
        .uri(ROUND_URL)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldGetLastNumber() {
    when(service.getLastNumber(anyString()))
        .thenReturn(Mono.just(123));
    when(mapper.toRoundNumberResponse(any()))
        .thenReturn(getNumberResponse());

    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_LAST_NUMBER)
        .buildAndExpand(ID_EXAMPLE)
        .toUriString();

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldNotGetLastNumberWithInvalidId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_LAST_NUMBER)
        .buildAndExpand("xpto")
        .toUriString();

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldNotGetLastNumberWithEmptyId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_LAST_NUMBER)
        .buildAndExpand(" ")
        .toUriString();

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldGenerateNumber() {
    when(service.generateNumber(anyString()))
        .thenReturn(Mono.just(getRound()));
    when(mapper.toRoundDrawResponse(any()))
        .thenReturn(getDrawResponse());

    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_NUMBER)
        .buildAndExpand(ID_EXAMPLE)
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldNotGenerateNumberWithInvalidId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_NUMBER)
        .buildAndExpand("xpto")
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldNotGenerateNumberWithEmptyId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_NUMBER)
        .buildAndExpand(" ")
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldGenerateCard() {
    when(service.generateCard(anyString(), anyString()))
        .thenReturn(Mono.just(generateCardResponse()));
    when(mapper.toRoundCardResponse(any()))
        .thenReturn(getRoundCardOnlyResponse());

    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_CARD)
        .buildAndExpand(ID_EXAMPLE, ID_EXAMPLE)
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldNotGenerateCardWithInvalidId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_CARD)
        .buildAndExpand("xpto", "xpto")
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldNotGenerateCardWithEmptyId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_CARD)
        .buildAndExpand(" ", ID_EXAMPLE)
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldNotGenerateCardWithEmptyDrawId() {
    var uri = UriComponentsBuilder
        .fromUriString(ROUND_URL_GENERATE_CARD)
        .buildAndExpand(ID_EXAMPLE, " ")
        .toUriString();

    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  private Pair<RoundCardDTO, String> generateCardResponse() {
    return Pair.of(RoundCardDTO.builder().build(), ID_EXAMPLE);
  }

  private RoundCardOnlyResponseDTO getRoundCardOnlyResponse() {
    return RoundCardOnlyResponseDTO.builder().build();
  }

  private RoundDrawResponseDTO getDrawResponse() {
    return RoundDrawResponseDTO.builder()
        .winners(List.of())
        .drawnNumbers(List.of())
        .build();
  }

  private RoundNumberResponseDTO getNumberResponse() {
    return RoundNumberResponseDTO.builder().number(123).build();
  }

  private PageResponseDTO<RoundResponseDTO> getReturnSuccessPageResponse() {
    var list = List.of(getResponse());

    return new PageResponseDTO<>(list, true, 1, 1L, false, 0, 1);
  }

  private PageImpl<RoundDTO> getReturnSuccessList() {
    var list = List.of(getRound());
    var pageable = PageRequest.of(0,10);
    return new PageImpl<>(list,pageable,1);
  }

  private RoundDTO getRound() {
    return RoundDTO.builder().build();
  }

  private RoundResponseDTO getResponse() {
    return RoundResponseDTO.builder().build();
  }
}
