package org.example.gof.strategy;

public class NormalBehavior implements Behavior {
  @Override
  public void move() {
    System.out.println("I'm going to walk");
  }
}
