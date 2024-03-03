package org.example.gof.subsystem;

import org.example.gof.facade.Facade;

public class Main {

  public static void main(String[] args) {
    Facade facade = new Facade();
    facade.changeClientAddress("John", "12345678");
  }
}
