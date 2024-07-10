package org.example.bingowebflux.domain.dto.round;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static org.example.bingowebflux.domain.constant.Descriptions.ROUND_PLAYER_ID;
import static org.example.bingowebflux.domain.constant.Descriptions.ROUND_PLAYER_NICKNAME;

@Builder(toBuilder = true)
public record RoundPlayerResponseDTO(
    @Schema(description = ROUND_PLAYER_ID)
    String id,

    @Schema(description = ROUND_PLAYER_NICKNAME)
    String nickname
) {
}
