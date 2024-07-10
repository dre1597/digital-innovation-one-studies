package org.example.bingowebflux.api.controller.player;

import lombok.RequiredArgsConstructor;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerUpdateRequestDTO;
import org.example.bingowebflux.domain.mapper.player.PlayerMapper;
import org.example.bingowebflux.service.player.PlayerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class PlayerController implements PlayerAPI {
  private final PlayerService service;
  private final PlayerMapper mapper;

  @Override
  public Mono<PageResponseDTO<PlayerResponseDTO>> getList(int page, int size) {
    return service.findAll(PageRequest.of(page, size))
        .map(mapper::toPage);
  }

  @Override
  public Mono<PlayerResponseDTO> getById(String id) {
    return service.getById(id)
        .map(mapper::toResponse);
  }

  @Override
  public Mono<PlayerResponseDTO> create(String nickname) {
    return service.create(nickname)
        .map(mapper::toResponse);
  }

  @Override
  public Mono<PlayerResponseDTO> update(PlayerUpdateRequestDTO request) {
    return Mono.just(request)
        .map(mapper::toPlayer)
        .flatMap(service::update)
        .map(mapper::toResponse);
  }

  @Override
  public Mono<Void> delete(String id) {
    return service.delete(id);
  }
}

