package org.example.bingowebflux.api.controller.player;

import org.example.bingowebflux.api.controller.ControllerTest;
import org.example.bingowebflux.api.exception.CustomExceptionHandler;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.dto.player.PlayerResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerUpdateRequestDTO;
import org.example.bingowebflux.domain.mapper.player.PlayerMapper;
import org.example.bingowebflux.service.player.PlayerService;
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

import static org.example.bingowebflux.domain.constant.Constants.PAGE;
import static org.example.bingowebflux.domain.constant.Constants.SIZE;
import static org.example.bingowebflux.domain.constant.ErrorMessages.GENERIC_REQUIRED;
import static org.example.bingowebflux.domain.constant.Examples.ID_EXAMPLE;
import static org.example.bingowebflux.domain.constant.Examples.PLAYER_NICKNAME_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PlayerController.class, CustomExceptionHandler.class})
@WebFluxTest(controllers = PlayerController.class)
class PlayerControllerTest extends ControllerTest {
  private static final String PLAYER_URL = "/players";
  private static final String PLAYER_URL_ID = "/players/{id}";
  private static final String PLAYER_URL_NICKNAME = "/players/{nickname}";

  @MockBean
  private PlayerService service;
  @MockBean
  private PlayerMapper mapper;

  @Test
  void shouldGetAllPlayers() {
    var uri = UriComponentsBuilder
        .fromUriString(PLAYER_URL)
        .queryParam(PAGE, 0)
        .queryParam(SIZE, 10)
        .toUriString();

    when(service.findAll(any()))
        .thenReturn(Mono.just(getReturnSuccessList()));

    when(mapper.toPage(any()))
        .thenReturn(getReturnSuccessPageResponse());

    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldGetPlayerById() {
    var uri = UriComponentsBuilder
        .fromUriString(PLAYER_URL_ID)
        .buildAndExpand(ID_EXAMPLE)
        .toUriString();

    when(service.getById(anyString()))
        .thenReturn(Mono.just(getPlayer()));

    when(mapper.toResponse(any()))
        .thenReturn(getPlayerResponse());


    this.client
        .get()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldCreateAPlayer() {
    var uri = UriComponentsBuilder
        .fromUriString(PLAYER_URL_NICKNAME)
        .buildAndExpand(PLAYER_NICKNAME_EXAMPLE)
        .toUriString();

    when(service.create(anyString()))
        .thenReturn(Mono.just(getPlayer()));

    when(mapper.toResponse(any()))
        .thenReturn(getPlayerResponse());


    this.client
        .post()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isCreated();
  }

  @Test
  void shouldUpdateAPlayer() {

    when(mapper.toPlayer(any(PlayerUpdateRequestDTO.class)))
        .thenReturn(getPlayer());


    when(service.update(any()))
        .thenReturn(Mono.just(getPlayer()));

    when(mapper.toResponse(any()))
        .thenReturn(getPlayerResponse());


    this.client
        .put()
        .uri(PLAYER_URL)
        .bodyValue(getUpdateRequest().build())
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shouldDeleteAPlayer() {

    var uri = UriComponentsBuilder
        .fromUriString(PLAYER_URL_ID)
        .buildAndExpand(ID_EXAMPLE)
        .toUriString();

    when(service.delete(any()))
        .thenReturn(Mono.empty());

    this.client
        .delete()
        .uri(uri)
        .exchange()
        .expectStatus()
        .isNoContent();
  }

  @Test
  void shouldNotUpdateAPlayerWhenRequiredFieldsAreEmpty() {
    assertTrue(this.violation(getUpdateRequest().id(null).build()).stream().anyMatch(u -> u.getMessageTemplate().equals(GENERIC_REQUIRED)));
    assertTrue(this.violation(getUpdateRequest().nickname(null).build()).stream().anyMatch(u -> u.getMessageTemplate().equals(GENERIC_REQUIRED)));
  }

  private PlayerUpdateRequestDTO.PlayerUpdateRequestDTOBuilder getUpdateRequest() {
    return PlayerUpdateRequestDTO.builder()
        .id(ID_EXAMPLE)
        .nickname(PLAYER_NICKNAME_EXAMPLE);
  }

  private PlayerResponseDTO getPlayerResponse() {
    return PlayerResponseDTO
        .builder()
        .id(ID_EXAMPLE)
        .nickname(PLAYER_NICKNAME_EXAMPLE)
        .build();
  }

  private PlayerDTO getPlayer() {
    return PlayerDTO.builder()
        .id(ID_EXAMPLE)
        .nickname(PLAYER_NICKNAME_EXAMPLE)
        .build();
  }

  private PageResponseDTO<PlayerResponseDTO> getReturnSuccessPageResponse() {
    var list = List.of(getPlayerResponse());

    return new PageResponseDTO<>(list, true, 1, 1L, false, 0, 1);
  }

  private PageImpl<PlayerDTO> getReturnSuccessList() {
    var list = List.of(getPlayer());
    var pageable = PageRequest.of(0,10);
    return new PageImpl<>(list,pageable,1);
  }
}
