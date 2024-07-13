package org.example.bingowebflux.domain.executor;

import org.apache.commons.lang3.tuple.Pair;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.dto.round.RoundCardDTO;
import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

import static org.example.bingowebflux.domain.constant.Examples.ID_EXAMPLE;

@ExtendWith(MockitoExtension.class)
class GenerateCardExecutorTest {
  @InjectMocks
  private GenerateCardExecutor executor;

  @Test
  void createCardTest() {
    var result = executor.execute(Pair.of(getRound(), getPlayer()));

    StepVerifier.create(result)
        .expectNextMatches(Objects::nonNull)
        .verifyComplete();
  }

  private PlayerDTO getPlayer() {
    return PlayerDTO.builder()
        .id(ID_EXAMPLE)
        .build();
  }

  private RoundDTO getRound() {
    return RoundDTO.builder()
        .drawnNumbers(new ArrayList<>(List.of(1,2,3)))
        .cards(new ArrayList<>(List.of(getCard(), getCard(), getCard(), getCard())))
        .build();
  }

  private RoundCardDTO getCard() {
    var drawnNumbers = IntStream.range(0,30)
        .map(n -> new Random().nextInt(100))
        .boxed()
        .toList();

    return RoundCardDTO.builder()
        .numbers(new ArrayList<>(drawnNumbers))
        .build();
  }
}
