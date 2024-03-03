package org.example.gof.strategy;

public class Main {

  public static void main(String[] args) {
    Robot robot = new Robot();
    robot.setBehavior(new NormalBehavior());
    robot.move();
    robot.setBehavior(new DefensiveBehavior());
    robot.move();
    robot.setBehavior(new AggressiveBehavior());
    robot.move();
    robot.move();
  }
}
