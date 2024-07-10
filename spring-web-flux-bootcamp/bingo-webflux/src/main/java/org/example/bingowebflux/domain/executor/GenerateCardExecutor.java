package org.example.bingowebflux.domain.executor;

import org.apache.commons.lang3.tuple.Pair;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.dto.round.RoundCardDTO;
import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.example.bingowebflux.domain.dto.round.RoundPlayerDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class GenerateCardExecutor extends AbstractExecutor {

  public Mono<Pair<RoundDTO, RoundCardDTO>> execute(Pair<RoundDTO, PlayerDTO> playerPair) {
    return Mono.just(playerPair.getRight())
        .map(this::createCard)
        .flatMap(card -> this.generateNumbers(playerPair.getLeft(), card));
  }

  private Mono<Pair<RoundDTO, RoundCardDTO>> generateNumbers(RoundDTO round, RoundCardDTO card) {
    var generated = new ArrayList<Integer>();
    return Flux.create((FluxSink<Integer> sink) -> {
          IntStream.range(0,20).forEach(e -> {
            var next = generateNumber(generated, round.getCards());
            generated.add(next);
            sink.next(next);
          });
          sink.complete();
        })
        .sort()
        .collectList()
        .map(list -> card.getNumbers().addAll(list))
        .map(x -> round.getCards().add(card))
        .map(x -> Pair.of(round, card));
  }

  private RoundCardDTO createCard(PlayerDTO player) {
    return RoundCardDTO.builder()
        .owner(RoundPlayerDTO.builder()
            .id(player.id())
            .nickname(player.nickname())
            .build()
        )
        .build();
  }

  private Integer generateNumber(List<Integer> generated, List<RoundCardDTO> roundCards) {
    var next = this.generateNewUnprecedentedNumber(generated);
    var list = new ArrayList<>(generated);
    list.add(next);

    if(roundCards.stream().anyMatch(rc -> rc.countsRepeatedNumbers(list) > 5))
      return generateNumber(generated, roundCards);
    return next;

  }


}

