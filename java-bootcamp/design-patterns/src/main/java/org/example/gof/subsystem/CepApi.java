package org.example.gof.subsystem;

public class CepApi {
  public static CepApi instance = new CepApi();

  private CepApi() {}

  public static CepApi getInstance() {
    return instance;
  }

  public String getCity(String zipCode) {
    return "city";
  }

  public String getState(String zipCode) {
    return "state";
  }
}
