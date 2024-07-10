package org.example.bingowebflux.domain.dto.player;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.example.bingowebflux.api.validation.ObjectIdIsValid;

import static org.example.bingowebflux.domain.constant.Descriptions.PLAYER_FIELD_ID;
import static org.example.bingowebflux.domain.constant.Descriptions.PLAYER_FIELD_NICKNAME;
import static org.example.bingowebflux.domain.constant.ErrorMessages.GENERIC_REQUIRED;


@Builder(toBuilder = true)
public record PlayerUpdateRequestDTO(
    @Schema(description = PLAYER_FIELD_ID)
    @NotBlank(message = GENERIC_REQUIRED)
    @ObjectIdIsValid
    String id,

    @Schema(description = PLAYER_FIELD_NICKNAME)
    @NotBlank(message = GENERIC_REQUIRED)
    String nickname
) {
}

