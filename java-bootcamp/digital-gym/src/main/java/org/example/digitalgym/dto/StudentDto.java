package org.example.digitalgym.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentDto(
    @NotEmpty
    @Size(min = 3, max = 50)
    String name,

    @NotEmpty
    @Size(min = 11, max = 11)
    String cpf,

    @NotEmpty
    @Size(min = 3, max = 50)
    String neighborhood,

    @NotNull
    @Past
    LocalDate birthDate
) {
}
