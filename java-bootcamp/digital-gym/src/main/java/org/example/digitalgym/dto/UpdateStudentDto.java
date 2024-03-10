package org.example.digitalgym.dto;

import java.time.LocalDate;

public record UpdateStudentDto(String name, String cpf, String neighborhood, LocalDate birthDate) {
}
