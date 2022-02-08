package com.stopark.services;

import com.stopark.models.entities.Cliente;
import com.stopark.models.entities.Endereco;
import com.stopark.models.request.ClienteRequest;
import com.stopark.repository.ClienteRepository;
import com.stopark.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepService viaCepService;



    public Cliente adicionarCliente(Cliente cliente) {
    vincularClienteCep(cliente);

    return cliente;
    }
    public Iterable<Cliente> listarTodosOsClientes() {return clienteRepository.findAll();}

    private void vincularClienteCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
        Endereco novoEndereco = viaCepService.consultarCep(cep);
        enderecoRepository.save(novoEndereco);
        return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
