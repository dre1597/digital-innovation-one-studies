package org.example.digitalgym.controller;

import jakarta.validation.Valid;
import org.example.digitalgym.dto.RegistrationDto;
import org.example.digitalgym.entity.Registration;
import org.example.digitalgym.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
  private final RegistrationService registrationService;

  public RegistrationController(final RegistrationService registrationService) {
    this.registrationService = Objects.requireNonNull(registrationService);
  }

  @GetMapping
  public List<Registration> getAll(@RequestParam(value = "neighborhood", required = false) final String neighborhood) {
    return registrationService.getAll(neighborhood);
  }

  @GetMapping(value = "/{id}")
  public Registration getOne(@PathVariable final Long id) {
    return registrationService.getOne(id);
  }

  @PostMapping
  public void create(@Valid @RequestBody final RegistrationDto dto) {
    registrationService.create(dto);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable final Long id) {
    registrationService.delete(id);
  }
}
