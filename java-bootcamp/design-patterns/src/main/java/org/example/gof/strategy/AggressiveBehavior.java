package org.example.gof.strategy;

public class AggressiveBehavior implements Behavior {
  @Override
  public void move() {
    System.out.println("I'm going to attack");
  }
}
