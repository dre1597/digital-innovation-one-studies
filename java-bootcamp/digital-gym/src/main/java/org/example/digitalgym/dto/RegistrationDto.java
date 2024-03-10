package org.example.digitalgym.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrationDto(
    @NotNull
    @Positive
    Long studentId
) {
}
