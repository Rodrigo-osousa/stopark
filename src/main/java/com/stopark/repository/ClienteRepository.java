package com.stopark.repository;

import com.stopark.models.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends CrudRepository <Cliente, String> {
}
