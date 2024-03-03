package org.example.domain;

import java.time.LocalDate;

public class Mentoring  extends Content {
  private LocalDate date;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(final LocalDate date) {
    this.date = date;
  }

  @Override
  public double calculateExp() {
    return DEFAULT_EXP + 20d;
  }

  @Override
  public String toString() {
    return "Mentoring{" +
        "title='" + getTitle() + '\'' +
        ", description='" + getDescription() + '\'' +
        ", date=" + date +
        '}';
  }
}
