package org.example.reactiveflashcards.api.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.example.reactiveflashcards.core.validation.MongoId;

public record StudyRequest(
    @MongoId
    @JsonProperty("userId")
    @Schema(description = "identificador do usuário", example = "62bdec5e5a8f2441c4d27460", format = "UUID")
    String userId,
    @MongoId
    @JsonProperty("deckId")
    @Schema(description = "identificador do deck", example = "62bdec5e5a8f2441c4d27461", format = "UUID")
    String deckId
) {

  @Builder(toBuilder = true)
  public StudyRequest {
  }

}
