package org.example.gof.subsystem;

public class CrmService {

  private CrmService() {

  }

  public static void saveCustomer(String name, String zipCode, String city, String state) {
    System.out.println("Saved customer: ");
    System.out.println(name);
    System.out.println(zipCode);
    System.out.println(city);
    System.out.println(state);
  }
}
