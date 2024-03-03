package org.example.gof.strategy;

public class DefensiveBehavior implements Behavior {
  @Override
  public void move() {
    System.out.println("I'm going to defend");
  }
}
