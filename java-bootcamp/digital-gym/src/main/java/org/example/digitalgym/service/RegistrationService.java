package org.example.digitalgym.service;

import org.example.digitalgym.dto.RegistrationDto;
import org.example.digitalgym.entity.Registration;

import java.util.List;

public interface RegistrationService {
  List<Registration> getAll(final String neighborhood);

  Registration getOne(final Long id);

  void create(final RegistrationDto dto);

  void delete(final Long id);
}
