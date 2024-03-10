package org.example.digitalgym.repository;

import org.example.digitalgym.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  List<Student> findByBirthDate(LocalDate birthDate);
}
