package org.example.digitalgym.repository;

import org.example.digitalgym.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

  @Query(value = "SELECT r.* FROM registration r INNER JOIN student s ON r.student_id = s.id WHERE s.neighborhood = :neighborhood", nativeQuery = true)
  List<Registration> findStudentsRegisteredPerNeighborhood(final String neighborhood);
}
