package org.example.digitalgym.service.impl;

import org.example.digitalgym.dto.RegistrationDto;
import org.example.digitalgym.entity.Registration;
import org.example.digitalgym.repository.RegistrationRepository;
import org.example.digitalgym.repository.StudentRepository;
import org.example.digitalgym.service.RegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegistrationServiceImpl implements RegistrationService {
  private final RegistrationRepository repository;
  private final StudentRepository studentRepository;

  public RegistrationServiceImpl(final RegistrationRepository repository, final StudentRepository studentRepository) {
    this.repository = Objects.requireNonNull(repository);
    this.studentRepository = Objects.requireNonNull(studentRepository);
  }

  @Override
  public List<Registration> getAll(final String neighborhood) {
    if (neighborhood == null) {
      return repository.findAll();
    }

    return repository.findStudentsRegisteredPerNeighborhood(neighborhood);
  }


  @Override
  public Registration getOne(final Long id) {
    return repository.findById(id).orElse(null);
  }


  @Override
  public void create(final RegistrationDto dto) {
    final var entity = new Registration();
    final var student = studentRepository.findById(dto.studentId()).orElse(null);

    if (student == null) {
      throw new IllegalArgumentException("Student not found");
    }

    entity.setStudent(student);
    repository.save(entity);
  }


  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
