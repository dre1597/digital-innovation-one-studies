package org.example.bingowebflux.api.controller.round;

import lombok.RequiredArgsConstructor;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundCardOnlyResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundDrawResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundNumberResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundResponseDTO;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;
import org.example.bingowebflux.domain.mapper.round.RoundMapper;
import org.example.bingowebflux.service.round.RoundService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RoundController implements RoundAPI {
  private final RoundService service;
  private final RoundMapper mapper;

  public Mono<PageResponseDTO<RoundResponseDTO>> getList(
      int page,
      int size,
      String status
  ) {
    return service.findAll(RoundStatus.getByName(status), PageRequest.of(page, size))
        .map(mapper::toPage);
  }

  public Mono<RoundResponseDTO> getById(String id) {
    return service.getById(id)
        .map(mapper::toResponse);
  }

  public Mono<RoundNumberResponseDTO> getLastNumber(String id) {
    return service.getLastNumber(id)
        .map(mapper::toRoundNumberResponse);
  }

  public Mono<RoundResponseDTO> create() {
    return service.create()
        .map(mapper::toResponse);
  }

  public Mono<RoundDrawResponseDTO> generateNumber(String id) {
    return service.generateNumber(id)
        .map(mapper::toRoundDrawResponse);
  }

  public Mono<RoundCardOnlyResponseDTO> generateCard(String id, String playerId
  ) {
    return service.generateCard(id, playerId)
        .map(mapper::toRoundCardResponse);
  }
}

