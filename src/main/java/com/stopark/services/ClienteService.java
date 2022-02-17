package com.stopark.services;

import com.stopark.models.entities.Cliente;
import com.stopark.models.entities.Endereco;
import com.stopark.models.request.ClienteRequest;
import com.stopark.repository.CarroRepository;
import com.stopark.repository.ClienteRepository;
import com.stopark.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepService viaCep;

    @Autowired
    CarroRepository carroRepository;

    private static Logger logger = Logger.getLogger(ClienteService.class.getName());

    public ClienteRequest adicionarCliente(ClienteRequest clienteRequest) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findByNumeroDoDocumento(clienteRequest.getNumeroDoDocumento());
        if (cliente.isPresent()) {
            throw new Exception("Cliente já existe");
        }
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteRequest.getNome());
        novoCliente.setNumeroDoDocumento(clienteRequest.getNumeroDoDocumento());
        Endereco endereco = buscarCep(clienteRequest);
        novoCliente.setEndereco(endereco);

        clienteRepository.save(novoCliente);
        clienteRequest.setEndereco(endereco);
        return clienteRequest;
    }

    public Iterable<Cliente> listarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorNumeroDoDocumento(String numeroDoDocumento) {
        return clienteRepository.findByNumeroDoDocumento(numeroDoDocumento);
    }

    public ClienteRequest atualizarCliente(ClienteRequest clienteRequest) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(clienteRequest.getId());

        if (cliente.isEmpty()) {
            throw new Exception("Cliente ou não existe");
        }
        cliente.get().setNumeroDoDocumento(clienteRequest.getNumeroDoDocumento());
        cliente.get().setNome(clienteRequest.getNome());
        Endereco novoEndereco = buscarCep(clienteRequest);
        cliente.get().setEndereco(novoEndereco);
        clienteRepository.save(cliente.get());
        clienteRequest.setEndereco(novoEndereco);
        return clienteRequest;

    }

    public void deletarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    private Endereco buscarCep(ClienteRequest clienteRequest) {
        String cep = clienteRequest.getEndereco().getCep();
        Endereco novoEndereco = viaCep.buscarEndereco(cep);
        enderecoRepository.save(novoEndereco);
        return novoEndereco;
    }
}


