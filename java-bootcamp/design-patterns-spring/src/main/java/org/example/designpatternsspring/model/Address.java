package org.example.designpatternsspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.designpatternsspring.dto.ViaCepRO;

@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String cep;
  private String street;
  private String complement;
  private String neighborhood;
  private String city;
  private String state;

  public static Address fromViaCepRO(final ViaCepRO viaCepRO) {
    final var address = new Address();

    address.setCep(viaCepRO.cep());
    address.setStreet(viaCepRO.logradouro());
    address.setComplement(viaCepRO.complemento());
    address.setNeighborhood(viaCepRO.bairro());
    address.setCity(viaCepRO.localidade());
    address.setState(viaCepRO.uf());

    return address;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(final String cep) {
    this.cep = cep;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(final String street) {
    this.street = street;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(final String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(final String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(final String state) {
    this.state = state;
  }
}
