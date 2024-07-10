package org.example.bingowebflux.domain.executor;

import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.example.bingowebflux.domain.enumeration.round.RoundStatus.FINISHED;
import static org.example.bingowebflux.domain.enumeration.round.RoundStatus.RUNNING;

@Component
public class DrawExecutor extends AbstractExecutor {


  public Mono<RoundDTO> execute(RoundDTO round) {
    return Mono.just(round)
        .flatMap(this::drawNumber)
        .flatMap(this::thereAreChampions);
  }

  private Mono<RoundDTO> thereAreChampions(RoundDTO round) {
    return Flux.fromIterable(round.getCards())
        .filter(card -> card.isWinner(round.getDrawnNumbers()))
        .collectList()
        .filter(winners -> !winners.isEmpty())
        .map(winners -> {
          round.getWinners().addAll(winners);
          round.setStatus(FINISHED);
          return round;
        })
        .defaultIfEmpty(round);
  }

  private Mono<RoundDTO> drawNumber(RoundDTO round) {
    return Mono.just(this.generateNewUnprecedentedNumber(round.getDrawnNumbers()))
        .map(number ->{
          round.setStatus(RUNNING);
          round.setLastDraw(number);
          round.getDrawnNumbers().add(number);
          return round;
        });
  }
}
