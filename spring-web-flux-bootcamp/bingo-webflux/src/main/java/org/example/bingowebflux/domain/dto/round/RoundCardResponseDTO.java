package org.example.bingowebflux.domain.dto.round;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static org.example.bingowebflux.domain.constant.Descriptions.ROUND_CARD_NUMBERS;
import static org.example.bingowebflux.domain.constant.Descriptions.ROUND_CARD_OWNER;

@Builder(toBuilder = true)
public record RoundCardResponseDTO(
    @Schema(description = ROUND_CARD_NUMBERS)
    List<Integer> numbers,

    @Schema(description = ROUND_CARD_OWNER)
    RoundPlayerResponseDTO owner
) {
}

