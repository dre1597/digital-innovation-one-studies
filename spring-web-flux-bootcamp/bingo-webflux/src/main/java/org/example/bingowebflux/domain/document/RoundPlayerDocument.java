package org.example.bingowebflux.domain.document;

import lombok.Builder;

@Builder(toBuilder = true)
public record RoundPlayerDocument(
    String id,
    String nickname
) {
}
