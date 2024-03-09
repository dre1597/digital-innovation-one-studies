package org.example.designpatternsspring.service;

import org.example.designpatternsspring.dto.CreateCustomerDTO;
import org.example.designpatternsspring.model.Customer;

public interface CustomerService {
  Iterable<Customer> getAll();

  Customer getById(Long id);

  void create(CreateCustomerDTO customer);

  void update(Long id, CreateCustomerDTO customer);

  void delete(Long id);
}
