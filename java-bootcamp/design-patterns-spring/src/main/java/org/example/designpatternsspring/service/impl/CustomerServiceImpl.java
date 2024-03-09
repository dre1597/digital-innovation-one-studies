package org.example.designpatternsspring.service.impl;

import jakarta.transaction.Transactional;
import org.example.designpatternsspring.dto.CreateCustomerDTO;
import org.example.designpatternsspring.model.Address;
import org.example.designpatternsspring.model.Customer;
import org.example.designpatternsspring.repository.AddressRepository;
import org.example.designpatternsspring.repository.CustomerRepository;
import org.example.designpatternsspring.service.CustomerService;
import org.example.designpatternsspring.service.ViaCepService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  private final AddressRepository addressRepository;

  private final ViaCepService viaCepService;

  public CustomerServiceImpl(
      final CustomerRepository customerRepository,
      final AddressRepository addressRepository,
      final ViaCepService viaCepService
  ) {
    this.customerRepository = Objects.requireNonNull(customerRepository);
    this.addressRepository = Objects.requireNonNull(addressRepository);
    this.viaCepService = Objects.requireNonNull(viaCepService);
  }

  @Override
  public Iterable<Customer> getAll() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getById(Long id) {
    return customerRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void create(CreateCustomerDTO dto) {
    final var viaCepRO = viaCepService.getAddressByCep(dto.cep());

    Customer customer = new Customer();
    customer.setName(dto.name());

    final var address = Address.fromViaCepRO(viaCepRO);
    addressRepository.save(address);
    customer.setAddress(address);

    customerRepository.save(customer);
  }

  @Override
  @Transactional
  public void update(Long id, CreateCustomerDTO dto) {
    final var customerToUpdate = customerRepository.findById(id);

    customerToUpdate.ifPresent(customer -> {
      customer.setName(dto.name());

      final var address = Address.fromViaCepRO(viaCepService.getAddressByCep(dto.cep()));
      addressRepository.save(address);
      customer.setAddress(address);

      customerRepository.save(customer);
    });

    if (customerToUpdate.isEmpty()) {
      throw new RuntimeException("Customer not found");
    }
  }

  @Override
  public void delete(Long id) {
    customerRepository.deleteById(id);
  }
}
