package org.example.digitalgym.service.impl;

import org.example.digitalgym.dto.PhysicalAssessmentDto;
import org.example.digitalgym.dto.UpdatePhysicalAssessmentDto;
import org.example.digitalgym.entity.PhysicalAssessment;
import org.example.digitalgym.repository.PhysicalAssessmentRepository;
import org.example.digitalgym.repository.StudentRepository;
import org.example.digitalgym.service.PhysicalAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PhysicalAssessmentServiceImpl implements PhysicalAssessmentService {
  private final PhysicalAssessmentRepository repository;
  private final StudentRepository studentRepository;

  public PhysicalAssessmentServiceImpl(final PhysicalAssessmentRepository repository, final StudentRepository studentRepository) {
    this.repository = Objects.requireNonNull(repository);
    this.studentRepository = Objects.requireNonNull(studentRepository);
  }

  @Override
  public List<PhysicalAssessment> getAll() {
    return repository.findAll();
  }

  @Override
  public void create(final PhysicalAssessmentDto dto) {
    final var entity = PhysicalAssessment.fromDto(dto);
    final var student = studentRepository.findById(dto.studentId()).orElse(null);

    if (student == null) {
      throw new RuntimeException("Student not found");
    }

    entity.setStudent(student);
    repository.save(entity);
  }

  @Override
  public PhysicalAssessment getOne(final Long id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public void update(final Long id, final UpdatePhysicalAssessmentDto dto) {
    final var entity = repository.getReferenceById(id);
    entity.setWeight(dto.weight());
    entity.setHeight(dto.height());
    repository.save(entity);
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
