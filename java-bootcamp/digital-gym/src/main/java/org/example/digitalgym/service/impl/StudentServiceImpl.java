package org.example.digitalgym.service.impl;

import org.example.digitalgym.dto.StudentDto;
import org.example.digitalgym.dto.UpdateStudentDto;
import org.example.digitalgym.entity.PhysicalAssessment;
import org.example.digitalgym.entity.Student;
import org.example.digitalgym.repository.StudentRepository;
import org.example.digitalgym.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {
  private final StudentRepository repository;

  public StudentServiceImpl(final StudentRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  @Override
  public List<Student> getAll(final String birthdate) {
    if (birthdate == null) {
      return repository.findAll();
    }

    final var localDate = LocalDate.parse(birthdate, DateTimeFormatter.ISO_DATE);
    return repository.findAllByBirthdate(localDate);
  }

  @Override
  public void create(final StudentDto dto) {
    repository.save(Student.fromDto(dto));
  }

  @Override
  public Student getOne(final Long id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public void update(final Long id, final UpdateStudentDto dto) {
    final var entity = repository.findById(id).orElse(null);

    if (entity == null) {
      throw new IllegalArgumentException("Student not found");
    }

    final var localDate = LocalDate.parse(dto.birthdate(), DateTimeFormatter.ISO_DATE);

    entity.setName(dto.name());
    entity.setCpf(dto.cpf());
    entity.setNeighborhood(dto.neighborhood());
    entity.setBirthdate(localDate);
    repository.save(entity);
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }

  @Override
  public List<PhysicalAssessment> getAllPhysicalAssessments(final Long id) {
    final var student = repository.findById(id);

    return student.map(Student::getPhysicalAssessments).orElse(null);
  }
}
