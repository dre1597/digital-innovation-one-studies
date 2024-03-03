package org.example.domain;

public abstract class Content {
  protected static final double DEFAULT_EXP = 10d;

  private String title;
  private String description;

  public abstract double calculateExp();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
