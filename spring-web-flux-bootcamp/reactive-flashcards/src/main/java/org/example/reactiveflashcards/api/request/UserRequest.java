package org.example.reactiveflashcards.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRequest(
    @NotBlank @Size(min = 3, max = 255) @JsonProperty("name") String name,
    @NotBlank @Size(min = 3, max = 255) @Email @JsonProperty("email") String email
) {
  @Builder(toBuilder = true)
  public UserRequest {
  }
}
