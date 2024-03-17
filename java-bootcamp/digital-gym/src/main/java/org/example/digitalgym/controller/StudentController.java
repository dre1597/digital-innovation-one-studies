package org.example.digitalgym.controller;

import jakarta.validation.Valid;
import org.example.digitalgym.dto.PhysicalAssessmentDto;
import org.example.digitalgym.dto.StudentDto;
import org.example.digitalgym.dto.UpdateStudentDto;
import org.example.digitalgym.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
public class StudentController {
  private final StudentService studentService;

  public StudentController(final StudentService studentService) {
    this.studentService = Objects.requireNonNull(studentService);
  }

  @GetMapping
  public List<StudentDto> getAll(@RequestParam(value = "birthdate", required = false) final String birthdate) {
    return StudentDto.fromListEntity(studentService.getAll(birthdate));
  }

  @GetMapping(value = "/{id}")
  public StudentDto getOne(@PathVariable final Long id) {
    return StudentDto.fromEntity(studentService.getOne(id));
  }

  @GetMapping("/physicalAssessments/{studentId}")
  public List<PhysicalAssessmentDto> getAllPhysicalAssessments(@PathVariable final Long studentId) {
    return PhysicalAssessmentDto.fromListEntity(studentService.getAllPhysicalAssessments(studentId));
  }

  @PostMapping
  public void create(@Valid @RequestBody final StudentDto dto) {
    studentService.create(dto);
  }

  @PutMapping(value = "/{id}")
  public void update(@PathVariable final Long id, @Valid @RequestBody final UpdateStudentDto dto) {
    studentService.update(id, dto);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable final Long id) {
    studentService.delete(id);
  }
}
