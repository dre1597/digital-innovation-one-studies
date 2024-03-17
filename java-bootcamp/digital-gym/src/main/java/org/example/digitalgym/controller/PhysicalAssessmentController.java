package org.example.digitalgym.controller;

import jakarta.validation.Valid;
import org.example.digitalgym.dto.PhysicalAssessmentDto;
import org.example.digitalgym.dto.UpdatePhysicalAssessmentDto;
import org.example.digitalgym.service.PhysicalAssessmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/physical-assessments")
public class PhysicalAssessmentController {
  private final PhysicalAssessmentService physicalAssessmentService;

  public PhysicalAssessmentController(final PhysicalAssessmentService physicalAssessmentService) {
    this.physicalAssessmentService = Objects.requireNonNull(physicalAssessmentService);
  }

  @GetMapping
  public List<PhysicalAssessmentDto> getAll() {
    return PhysicalAssessmentDto.fromListEntity(physicalAssessmentService.getAll());
  }

  @GetMapping(value = "/{id}")
  public PhysicalAssessmentDto getOne(@PathVariable final Long id) {
    return PhysicalAssessmentDto.fromEntity(physicalAssessmentService.getOne(id));
  }

  @PostMapping
  public void create(@Valid @RequestBody final PhysicalAssessmentDto dto) {
    physicalAssessmentService.create(dto);
  }

  @PutMapping(value = "/{id}")
  public void update(@PathVariable final Long id, @Valid @RequestBody final UpdatePhysicalAssessmentDto dto) {
    physicalAssessmentService.update(id, dto);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable final Long id) {
    physicalAssessmentService.delete(id);
  }
}
