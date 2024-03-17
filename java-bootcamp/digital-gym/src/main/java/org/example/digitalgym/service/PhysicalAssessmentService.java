package org.example.digitalgym.service;

import org.example.digitalgym.dto.PhysicalAssessmentDto;
import org.example.digitalgym.dto.UpdatePhysicalAssessmentDto;
import org.example.digitalgym.entity.PhysicalAssessment;

import java.util.List;

public interface PhysicalAssessmentService {

  List<PhysicalAssessment> getAll();

  PhysicalAssessment getOne(final Long id);

  void create(final PhysicalAssessmentDto dto);

  void update(final Long id, final UpdatePhysicalAssessmentDto dto);

  void delete(final Long id);
}
