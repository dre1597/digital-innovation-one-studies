package org.example.bingowebflux.domain.dto.round;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static org.example.bingowebflux.domain.constant.Descriptions.*;

@Builder(toBuilder = true)
public record RoundCardOnlyResponseDTO(
    @Schema(description = ROUND_ID_DESCRIPTION)
    String roundId,

    @Schema(description = ROUND_CARD_NUMBERS)
    List<Integer> numbers,

    @Schema(description = ROUND_CARD_OWNER)
    RoundPlayerResponseDTO owner
) {
}
