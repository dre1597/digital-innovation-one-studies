package org.example.reactiveflashcards.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DeckDTO(
    @JsonProperty("name") String name,
    @JsonProperty("info") String info,
    @JsonProperty("author") String author,
    @JsonProperty("cards") List<CardDTO> cards
) {
}
