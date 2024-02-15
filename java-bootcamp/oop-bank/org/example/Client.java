package org.example;

public record Client(String name) {

  @Override
  public String toString() {
    return "Client [name=" + name + "]";
  }
}
