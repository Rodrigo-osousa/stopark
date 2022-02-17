package com.stopark.repository;

import com.stopark.models.entities.Carro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CarroRepository extends CrudRepository<Carro, Integer> {
    Optional<Carro> findByPlaca(String placa);
}
