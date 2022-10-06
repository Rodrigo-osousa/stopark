package com.stopark.repository;

import com.stopark.models.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ClienteRepository extends CrudRepository <Cliente, Integer> {
  Optional<Cliente> findByNumeroDoDocumento(String numeroDoDocumento);
  List<Cliente> findAll();
}
