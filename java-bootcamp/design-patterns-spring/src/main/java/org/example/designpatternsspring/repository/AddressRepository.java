package org.example.designpatternsspring.repository;

import org.example.designpatternsspring.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
  Optional<Address> findByCep(String cep);
}
