package org.example.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {
  private String name;
  private Set<Content> subscriptions = new LinkedHashSet<>();
  private Set<Content> completed = new LinkedHashSet<>();

  public void subscribeBootcamp(final Bootcamp bootcamp) {
    this.subscriptions.addAll(bootcamp.getContents());
    bootcamp.getDevs().add(this);
  }

  public void toProgress() {
    final Optional<Content> content = this.subscriptions.stream().findFirst();

    if (content.isPresent()) {
      this.completed.add(content.get());
      this.subscriptions.remove(content.get());
    } else {
      System.err.println("You have no subscriptions");
    }
  }

  public double calculateTotalExp() {
    return this.completed
        .stream()
        .mapToDouble(Content::calculateExp)
        .sum();
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Set<Content> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(final Set<Content> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public Set<Content> getCompleted() {
    return completed;
  }

  public void setCompleted(final Set<Content> completed) {
    this.completed = completed;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Dev dev = (Dev) o;
    return Objects.equals(getName(), dev.getName()) && Objects.equals(getSubscriptions(), dev.getSubscriptions()) && Objects.equals(getCompleted(), dev.getCompleted());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getSubscriptions(), getCompleted());
  }
}
