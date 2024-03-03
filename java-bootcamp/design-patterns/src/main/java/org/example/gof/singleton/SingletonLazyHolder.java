package org.example.gof.singleton;

public class SingletonLazyHolder {
  private static class InstanceHolder {
    private static SingletonLazyHolder instance = new SingletonLazyHolder();
  }

  private SingletonLazyHolder() {}

  public static SingletonLazyHolder getInstance() {
    return InstanceHolder.instance;
  }
}
