package com.stopark.services;

import com.stopark.models.entities.Cliente;
import com.stopark.models.entities.Endereco;
import com.stopark.models.request.ClienteRequest;
import com.stopark.models.request.EnderecoRequest;
import com.stopark.repository.ClienteRepository;
import com.stopark.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepService viaCepService;

    public ClienteRequest adicionarCliente(ClienteRequest clienteRequest) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(clienteRequest.getCpf());
        if (cliente.isPresent()) {
            throw new Exception("Cliente já existe");
        }
        inserirClienteComCep(clienteRequest);

        return clienteRequest;
    }

    public Iterable<Cliente> listarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    private ClienteRequest inserirClienteComCep(ClienteRequest clienteRequest) {
        String cep = clienteRequest.getEndereco().getCep();
        Endereco novoEndereco = viaCepService.consultarCep(cep);
        novoEndereco.setComplemento(novoEndereco.getComplemento());
        enderecoRepository.save(novoEndereco);
        clienteRequest.setEndereco(novoEndereco);

        return clienteRequest;
    }
}


