package org.example.bingowebflux.domain.validator;

import org.apache.commons.lang3.tuple.Pair;
import org.example.bingowebflux.api.exception.BusinessException;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.dto.round.RoundDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static org.example.bingowebflux.domain.constant.ErrorMessages.ROUND_CREATE_CARD_EXISTS_CARD_FOR_PLAYER;
import static org.example.bingowebflux.domain.constant.ErrorMessages.ROUND_CREATE_CARD_INVALID_STATUS;
import static org.example.bingowebflux.domain.enumeration.round.RoundStatus.CREATED;

@Component
public class GenerateCardValidator {
  public Mono<Pair<RoundDTO, PlayerDTO>> validate(RoundDTO round, PlayerDTO player) {
    return Mono.just(Pair.of(round, player))
        .flatMap(this::validateStatus)
        .flatMap(this::validateExistsPlayer);
  }

  private Mono<Pair<RoundDTO, PlayerDTO>> validateStatus(Pair<RoundDTO, PlayerDTO> pair){
    if(!CREATED.equals(pair.getLeft().getStatus()))
      return Mono.error(new BusinessException(ROUND_CREATE_CARD_INVALID_STATUS));

    return Mono.just(pair);
  }

  private  Mono<Pair<RoundDTO, PlayerDTO>> validateExistsPlayer(Pair<RoundDTO, PlayerDTO> pair) {
    var round = pair.getLeft();
    var player = pair.getRight();

    if(round.getCards().stream().anyMatch(card -> card.getOwner().getId().equals(player.id())))
      return Mono.error(new BusinessException(ROUND_CREATE_CARD_EXISTS_CARD_FOR_PLAYER));

    return Mono.just(pair);
  }
}
