package org.example.digitalgym.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.example.digitalgym.entity.Student;

import java.time.LocalDate;
import java.util.List;

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
    LocalDate birthdate
) {
  public static StudentDto fromEntity(final Student entity) {
    return new StudentDto(
        entity.getName(),
        entity.getCpf(),
        entity.getNeighborhood(),
        entity.getBirthdate()
    );
  }

  public static List<StudentDto> fromListEntity(final List<Student> entities) {
    return entities.stream().map(StudentDto::fromEntity).toList();
  }
}
