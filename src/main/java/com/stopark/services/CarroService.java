package com.stopark.services;

import com.stopark.models.entities.Carro;
import com.stopark.models.request.CarroRequest;
import com.stopark.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {


    @Autowired
    CarroRepository carroRepository;

    public CarroRequest adicionarCarro(CarroRequest carroRequest) {
        Carro carro = new Carro();
        carro.setFabricacao(carroRequest.getFabricacao());
        carro.setMarca(carroRequest.getMarca());
        carro.setFabricacao(carroRequest.getFabricacao());

        carroRepository.save(carro);
        return carroRequest;

    }

}
