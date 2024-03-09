package org.example.designpatternsspring.controller;

import org.example.designpatternsspring.dto.CreateCustomerDTO;
import org.example.designpatternsspring.model.Customer;
import org.example.designpatternsspring.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(final CustomerService customerService) {
    this.customerService = Objects.requireNonNull(customerService);
  }

  @GetMapping
  public ResponseEntity<Iterable<Customer>> getAll() {
    return ResponseEntity.ok(customerService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getById(@PathVariable final Long id) {
    final var customer = customerService.getById(id);
    if (customer == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(customer);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody final CreateCustomerDTO dto) {
    customerService.create(dto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable final Long id, @RequestBody final CreateCustomerDTO dto) {
    customerService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long id) {
    customerService.delete(id);
  }
}
