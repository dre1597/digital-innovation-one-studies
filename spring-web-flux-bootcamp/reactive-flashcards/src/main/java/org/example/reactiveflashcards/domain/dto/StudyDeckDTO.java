package org.example.reactiveflashcards.domain.dto;

import lombok.Builder;

import java.util.Set;

public record StudyDeckDTO(String deckId, Set<StudyCardDTO> cards) {
  @Builder(toBuilder = true)
  public StudyDeckDTO {
  }
}
