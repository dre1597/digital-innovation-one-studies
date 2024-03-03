package org.example.domain;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Bootcamp {
  private String name;
  private String description;
  private final LocalDate startDate = LocalDate.now();
  private final LocalDate endDate = startDate.plusDays(45);
  final Set<Dev> devs = new LinkedHashSet<>();
  final Set<Content> contents = new LinkedHashSet<>();

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public Set<Dev> getDevs() {
    return devs;
  }

  public Set<Content> getContents() {
    return contents;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Bootcamp bootcamp = (Bootcamp) o;
    return Objects.equals(getName(), bootcamp.getName()) && Objects.equals(getDescription(), bootcamp.getDescription()) && Objects.equals(getStartDate(), bootcamp.getStartDate()) && Objects.equals(getEndDate(), bootcamp.getEndDate()) && Objects.equals(getDevs(), bootcamp.getDevs()) && Objects.equals(getContents(), bootcamp.getContents());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDescription(), getStartDate(), getEndDate(), getDevs(), getContents());
  }
}
