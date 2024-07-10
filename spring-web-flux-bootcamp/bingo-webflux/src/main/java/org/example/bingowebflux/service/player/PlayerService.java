package org.example.bingowebflux.service.player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.example.bingowebflux.api.exception.BusinessException;
import org.example.bingowebflux.api.exception.NotFoundException;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.mapper.player.PlayerMapper;
import org.example.bingowebflux.domain.repository.player.PlayerRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.example.bingowebflux.domain.constant.ErrorMessages.PLAYER_ALREADY_REGISTERED;
import static org.example.bingowebflux.domain.constant.ErrorMessages.PLAYER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {
  private final PlayerRepository repository;
  private final PlayerMapper mapper;

  public Mono<PlayerDTO> create(String nickname) {
    return this.repository.existsByNickname(nickname)
        .filter(FALSE::equals)
        .map(b -> mapper.toPlayer(nickname))
        .flatMap(this::save)
        .switchIfEmpty(Mono.error(new BusinessException(PLAYER_ALREADY_REGISTERED)))
        .doFirst(() -> log.info("Trying to create player"));
  }

  public Mono<PageImpl<PlayerDTO>> findAll(Pageable pageable) {
    return this.repository.count()
        .flatMap(total -> this.repository.findByIdNotNullOrderByNicknameAsc(pageable)
            .map(this.mapper::toPlayer)
            .collectList()
            .map(list -> new PageImpl<>(list, pageable, total))
        );
  }

  public Mono<PlayerDTO> getById(String id) {
    return Mono.just(id)
        .map(ObjectId::new)
        .flatMap(repository::findById)
        .map(mapper::toPlayer)
        .switchIfEmpty(Mono.error(new NotFoundException(PLAYER_NOT_FOUND)))
        .doFirst(() -> log.info("Trying to search for player by id"));
  }

  public Mono<PlayerDTO> update(PlayerDTO player) {
    return Mono.just(player)
        .map(p -> new ObjectId(p.id()))
        .flatMap(this.repository::existsById)
        .filter(TRUE::equals)
        .flatMap(b -> this.save(player))
        .switchIfEmpty(Mono.error(new NotFoundException(PLAYER_NOT_FOUND)));
  }

  public Mono<Void> delete(String id) {
    return getById(id)
        .map(mapper::toDocument)
        .flatMap(this.repository::delete);
  }

  private Mono<PlayerDTO> save(PlayerDTO player) {
    return Mono.just(player)
        .map(mapper::toDocument)
        .flatMap(repository::save)
        .map(mapper::toPlayer)
        .doFirst(() -> log.info("Saving player in database"));
  }
}
