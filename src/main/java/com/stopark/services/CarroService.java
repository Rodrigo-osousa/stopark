package com.stopark.services;

import com.stopark.models.entities.Carro;
import com.stopark.models.entities.Cliente;
import com.stopark.models.request.CarroRequest;
import com.stopark.repository.CarroRepository;
import com.stopark.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarroRepository carroRepository;


    public CarroRequest adicionarCarro(CarroRequest carroRequest) throws Exception {
        return novoCarro(carroRequest);
    }

    public Iterable<Carro> procurarTodosOsCarros() {
    return carroRepository.findAll();
    }


    public Optional<Carro> buscarCarroPelaPlaca(String placa) {
        return carroRepository.findByPlaca(placa);
    }

    private CarroRequest novoCarro(CarroRequest carroRequest) throws Exception {
        Optional<Cliente> buscarCliente = clienteRepository.findByNumeroDoDocumento(carroRequest.getNumeroDoDocumento());
        if (buscarCliente.isEmpty()) {
            throw new Exception("Cliente não existe");
        }

        Carro carro = new Carro();
        carro.setPlaca(carroRequest.getPlaca());
        carro.setFabricacao(carroRequest.getFabricacao());
        carro.setMarca(carroRequest.getMarca());
        carro.setModeloDoCarro(carroRequest.getModeloDoCarro());
        carro.setCliente(buscarCliente.get());

        carroRepository.save(carro);
        return carroRequest;
    }

}
