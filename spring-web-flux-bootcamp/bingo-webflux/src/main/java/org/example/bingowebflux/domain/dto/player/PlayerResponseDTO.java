package org.example.bingowebflux.domain.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static org.example.bingowebflux.domain.constant.Descriptions.PLAYER_FIELD_ID;
import static org.example.bingowebflux.domain.constant.Descriptions.PLAYER_FIELD_NICKNAME;

@Builder(toBuilder = true)
public record PlayerResponseDTO(
    @Schema(description = PLAYER_FIELD_ID)
    String id,

    @Schema(description = PLAYER_FIELD_NICKNAME)
    String nickname
) {
}
