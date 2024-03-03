package org.example.domain;

public class Course extends Content{
  private double duration;

  @Override
  public double calculateExp() {
    return DEFAULT_EXP * duration;
  }

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Course{" +
        "title='" + getTitle() + '\'' +
        ", description='" + getDescription() + '\'' +
        ", duration=" + duration +
        '}';
  }
}
