package org.example.bingowebflux.domain.dto.round;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;

import java.util.List;

import static org.example.bingowebflux.domain.constant.Descriptions.*;

@Builder(toBuilder = true)
public record RoundDrawResponseDTO(

    @Schema(description = ROUND_STATUS_DESCRIPTION)
    RoundStatus status,

    @Schema(description = ROUND_LAST_NUMBER)
    Integer lastDraw,

    @Schema(description = ROUND_QTD_DRAWN_NUMBERS)
    Integer qtdDrawnNumbers,

    @Schema(description = ROUND_WINNERS)
    List<RoundCardResponseDTO> winners,

    @Schema(description = ROUND_DRAWN_NUMBERS)
    List<Integer> drawnNumbers


) {
  @Override
  public List<Integer> drawnNumbers() {
    return drawnNumbers.stream().sorted().toList();
  }

  @Override
  public Integer qtdDrawnNumbers() {
    return drawnNumbers.size();
  }
}

