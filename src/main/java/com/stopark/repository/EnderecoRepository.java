package com.stopark.repository;

import com.stopark.models.entities.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EnderecoRepository extends CrudRepository<Endereco, String> {
    Optional<Endereco> findByCep(String cep);
}
