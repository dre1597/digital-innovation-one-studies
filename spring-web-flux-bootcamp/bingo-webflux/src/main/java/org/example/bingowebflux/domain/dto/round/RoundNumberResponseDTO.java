package org.example.bingowebflux.domain.dto.round;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static org.example.bingowebflux.domain.constant.Descriptions.ROUND_LAST_NUMBER;

@Builder(toBuilder = true)
public record RoundNumberResponseDTO(
    @Schema(description = ROUND_LAST_NUMBER)
    Integer number
) {
}

