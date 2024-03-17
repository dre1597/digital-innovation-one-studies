package org.example.digitalgym.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.example.digitalgym.dto.StudentDto;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String cpf;

  private String neighborhood;

  private LocalDate birthdate;

  @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<PhysicalAssessment> physicalAssessments = new ArrayList<>();

  public Student() {
  }

  public Student(final Long id, final String name, final String cpf, final String neighborhood, final LocalDate birthdate, final List<PhysicalAssessment> physicalAssessments) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.neighborhood = neighborhood;
    this.birthdate = birthdate;
    this.physicalAssessments = physicalAssessments;
  }

  public static Student fromDto(final StudentDto dto) {
    final var entity = new Student();
    entity.setName(dto.name());
    entity.setCpf(dto.cpf());
    entity.setNeighborhood(dto.neighborhood());
    entity.setBirthdate(dto.birthdate());
    return entity;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(final String cpf) {
    this.cpf = cpf;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(final String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(final LocalDate birthDate) {
    this.birthdate = birthDate;
  }

  public List<PhysicalAssessment> getPhysicalAssessments() {
    return physicalAssessments;
  }

  public void setPhysicalAssessments(final List<PhysicalAssessment> physicalAssessments) {
    this.physicalAssessments = physicalAssessments;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", cpf='" + cpf + '\'' +
        ", neighborhood='" + neighborhood + '\'' +
        ", birthdate=" + birthdate +
        ", physicalAssessments=" + physicalAssessments +
        '}';
  }

  @Override
  public final boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null) return false;
    final var oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    final var thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    final var student = (Student) o;
    return getId() != null && Objects.equals(getId(), student.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
