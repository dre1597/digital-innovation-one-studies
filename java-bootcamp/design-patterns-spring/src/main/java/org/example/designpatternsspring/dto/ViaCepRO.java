package org.example.designpatternsspring.dto;

public record ViaCepRO(
  String cep,
  String logradouro,
  String complemento,
  String bairro,
  String localidade,
  String uf
) {}
