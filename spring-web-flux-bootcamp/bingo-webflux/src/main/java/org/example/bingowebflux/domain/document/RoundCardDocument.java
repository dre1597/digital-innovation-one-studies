package org.example.bingowebflux.domain.document;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record RoundCardDocument(
    RoundPlayerDocument owner,
    List<Integer> numbers
) {
}
