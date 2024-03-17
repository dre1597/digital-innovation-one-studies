package org.example.digitalgym.entity;

import jakarta.persistence.*;
import org.example.digitalgym.dto.PhysicalAssessmentDto;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PhysicalAssessment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime evaluationData = LocalDateTime.now();

  @Column(name = "current_weight")
  private double weight;

  @Column(name = "current_height")
  private double height;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  public PhysicalAssessment() {
  }

  public PhysicalAssessment(final Long id, final LocalDateTime evaluationData, final double weight, final double height, final Student student) {
    this.id = id;
    this.evaluationData = evaluationData;
    this.weight = weight;
    this.height = height;
    this.student = student;
  }

  public static PhysicalAssessment fromDto(final PhysicalAssessmentDto dto) {
    final var entity = new PhysicalAssessment();
    entity.setWeight(dto.weight());
    entity.setHeight(dto.height());
    return entity;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public LocalDateTime getEvaluationData() {
    return evaluationData;
  }

  public void setEvaluationData(final LocalDateTime evaluationData) {
    this.evaluationData = evaluationData;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(final double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(final double height) {
    this.height = height;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(final Student student) {
    this.student = student;
  }

  @Override
  public String toString() {
    return "PhysicalAssessment{" +
        "id=" + id +
        ", evaluationData=" + evaluationData +
        ", weight=" + weight +
        ", height=" + height +
        ", student=" + student +
        '}';
  }

  @Override
  public final boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null) return false;
    final var oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    final var thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    final var that = (PhysicalAssessment) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
