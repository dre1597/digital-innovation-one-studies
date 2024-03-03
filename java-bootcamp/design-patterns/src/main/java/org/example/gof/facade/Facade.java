package org.example.gof.facade;

import org.example.gof.subsystem.CepApi;
import org.example.gof.subsystem.CrmService;

public class Facade {
  public void changeClientAddress(final String name, final String cep) {
    String city = CepApi.getInstance().getCity(cep);
    String state = CepApi.getInstance().getState(cep);

    CrmService.saveCustomer(name, cep, city, state);
  }
}
