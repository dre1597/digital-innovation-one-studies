package org.example.gof.singleton;

public class SingletonEager {
  private static SingletonEager instance = new SingletonEager();

  private SingletonEager() {}

  public static SingletonEager getInstance() {
    return instance;
  }
}
