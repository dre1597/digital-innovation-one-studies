package org.example.bingowebflux.domain.dto.player;

import lombok.Builder;

@Builder(toBuilder = true)
public record PlayerDTO(
    String id,
    String nickname
) {
}
