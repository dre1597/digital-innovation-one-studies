package org.example.digitalgym.service;

import org.example.digitalgym.dto.StudentDto;
import org.example.digitalgym.dto.UpdateStudentDto;
import org.example.digitalgym.entity.PhysicalAssessment;
import org.example.digitalgym.entity.Student;

import java.util.List;

public interface StudentService {
  List<Student> getAll(final String birthdate);

  void create(final StudentDto dto);

  Student getOne(final Long id);

  void update(final Long id, final UpdateStudentDto dto);

  void delete(final Long id);

  List<PhysicalAssessment> getAllPhysicalAssessments(final Long id);
}
