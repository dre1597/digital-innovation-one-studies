package org.example.bingowebflux.service.round;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;
import org.example.bingowebflux.api.exception.BusinessException;
import org.example.bingowebflux.api.exception.NotFoundException;
import org.example.bingowebflux.domain.dto.round.RoundCardDTO;
import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;
import org.example.bingowebflux.domain.executor.DrawExecutor;
import org.example.bingowebflux.domain.executor.GenerateCardExecutor;
import org.example.bingowebflux.domain.mapper.round.RoundMapper;
import org.example.bingowebflux.domain.repository.round.RoundRepository;
import org.example.bingowebflux.domain.validator.GenerateCardValidator;
import org.example.bingowebflux.service.player.PlayerService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.example.bingowebflux.domain.constant.ErrorMessages.*;
import static org.example.bingowebflux.domain.enumeration.round.RoundStatus.RUNNING;

@Slf4j
@Service
@AllArgsConstructor
public class RoundService {
  private RoundMapper mapper;
  private RoundRepository repository;
  private PlayerService playerService;
  private DrawExecutor drawExecutor;
  private GenerateCardValidator generateCardValidator;
  private GenerateCardExecutor generateCardExecutor;

  public Mono<RoundDTO> create() {
    return Mono.just(RoundDTO.builder().build())
        .flatMap(this::save)
        .doFirst(() -> log.info("Creating new round record"));
  }

  public Mono<PageImpl<RoundDTO>> findAll(RoundStatus status, Pageable pageable) {
    return this.repository.countByStatus(status)
        .flatMap(total -> this.repository.findByStatus(status, pageable)
            .map(this.mapper::toRound)
            .collectList()
            .map(list -> new PageImpl<>(list, pageable, total))
        )
        .doFirst(() -> log.info("Search round by status in a paginated way"));
  }

  public Mono<RoundDTO> generateNumber(String id) {
    return getById(id)
        .filter(r -> r.getCards().size() > 1)
        .flatMap(r -> Mono.just(r)
            .filter(round -> round.getWinners().isEmpty())
            .flatMap(drawExecutor::execute)
            .flatMap(this::save)
            .defaultIfEmpty(r)
        )
        .switchIfEmpty(Mono.error(new BusinessException(ROUND_NUMBER_OF_INVALID_CARDS_FOR_THE_ROUND)));
  }

  public Mono<Pair<RoundCardDTO, String>> generateCard(String roundId, String playerId) {
    return getById(roundId)
        .flatMap(round -> playerService.getById(playerId)
            .flatMap(player -> generateCardValidator.validate(round, player))
            .flatMap(generateCardExecutor::execute)
            .flatMap(pair -> Mono.just(pair.getLeft())
                .flatMap(this::save)
                .map(saved -> Pair.of(pair.getRight(), saved.getId()))
            )
        );
  }

  public Mono<Integer> getLastNumber(String id) {
    return getById(id)
        .filter(round -> RUNNING.equals(round.getStatus()))
        .map(RoundDTO::getLastDraw)
        .switchIfEmpty(Mono.error(new BusinessException(ROUND_LAST_NUMBER_DRAWN_NOT_RUNNING)));
  }

  public Mono<RoundDTO> getById(String id) {
    return Mono.just(id)
        .map(ObjectId::new)
        .flatMap(repository::findById)
        .map(mapper::toRound)
        .switchIfEmpty(Mono.error(new NotFoundException(ROUND_NOT_FOUND)))
        .doFirst(() -> log.info("Trying to search for round by id"));
  }

  private Mono<RoundDTO> save(org.example.bingowebflux.domain.dto.round.RoundDTO round) {
    return Mono.just(round)
        .map(mapper::toDocument)
        .flatMap(repository::save)
        .map(mapper::toRound);
  }
}
