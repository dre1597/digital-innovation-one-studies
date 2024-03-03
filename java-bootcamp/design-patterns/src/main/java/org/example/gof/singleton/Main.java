package org.example.gof.singleton;

public class Main {
  public static void main(String[] args) {
    SingletonLazy lazy = SingletonLazy.getInstance();
    SingletonEager eager = SingletonEager.getInstance();
    SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstance();

    System.out.println(lazy);
    System.out.println(eager);
    System.out.println(lazyHolder);

    eager = SingletonEager.getInstance();
    System.out.println(eager);

    lazyHolder = SingletonLazyHolder.getInstance();
    System.out.println(lazyHolder);

    lazyHolder = SingletonLazyHolder.getInstance();
    System.out.println(lazyHolder);
  }
}
