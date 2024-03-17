package org.example.digitalgym.dto;

import org.example.digitalgym.entity.PhysicalAssessment;

import java.util.List;

public record PhysicalAssessmentDto(Long studentId, double weight, double height) {

  public static PhysicalAssessmentDto fromEntity(final PhysicalAssessment physicalAssessment) {
    return new PhysicalAssessmentDto(physicalAssessment.getStudent().getId(), physicalAssessment.getWeight(), physicalAssessment.getHeight());
  }

  public static List<PhysicalAssessmentDto> fromListEntity(final List<PhysicalAssessment> allPhysicalAssessments) {
    return allPhysicalAssessments.stream().map(PhysicalAssessmentDto::fromEntity).toList();
  }
}
