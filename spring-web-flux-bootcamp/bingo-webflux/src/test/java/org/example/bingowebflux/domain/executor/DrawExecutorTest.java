package org.example.bingowebflux.domain.executor;

import org.example.bingowebflux.domain.dto.round.RoundCardDTO;
import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
class DrawExecutorTest {
  @InjectMocks
  private DrawExecutor executor;

  @Test
  void  executeWinnerTest() {
    var drawnNumbers = IntStream.range(0,99).boxed().toList();
    var round = getRound()
        .cards(List.of(getCard().build(), getCard().build()))
        .drawnNumbers(new ArrayList<>(drawnNumbers))
        .build();

    var result = executor.execute(round);

    StepVerifier.create(result)
        .expectNextMatches(r -> !r.getWinners().isEmpty())
        .verifyComplete();
  }

  @Test
  void  executeNotWinnerTest() {
    var round = getRound().build();

    var result = executor.execute(round);

    StepVerifier.create(result)
        .expectNextMatches(r -> r.getWinners().isEmpty())
        .verifyComplete();
  }

  private RoundDTO.RoundDTOBuilder getRound() {
    var card1 = getCard()
        .numbers(new ArrayList<>(List.of(1,2,3,4)))
        .build();
    var card2 = getCard().build();

    return RoundDTO.builder()
        .drawnNumbers(new ArrayList<>(List.of(1,2,3)))
        .cards(List.of(card1, card2));
  }

  private RoundCardDTO.RoundCardDTOBuilder getCard() {
    var list = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,99);
    return RoundCardDTO.builder()
        .numbers(new ArrayList<>(list));
  }
}
