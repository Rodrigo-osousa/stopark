package com.stopark.services;

import com.stopark.controllers.ClienteController;
import com.stopark.models.entities.Cliente;
import com.stopark.models.entities.Endereco;
import com.stopark.models.request.ClienteRequest;
import com.stopark.repository.CarroRepository;
import com.stopark.repository.ClienteRepository;
import com.stopark.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepRetryService viaCep;

    @Autowired
    CarroRepository carroRepository;

    private static Logger logger = Logger.getLogger(ClienteService.class.getName());

    public Cliente adicionarCliente(ClienteRequest clienteRequest) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findByNumeroDoDocumento(clienteRequest.getNumeroDoDocumento());
        if (cliente.isPresent()) {
            throw new Exception("Cliente já existe");
        }
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteRequest.getNome());
        novoCliente.setNumeroDoDocumento(clienteRequest.getNumeroDoDocumento());
        Endereco endereco = buscarCep(clienteRequest);
        endereco.setComplemento(clienteRequest.getComplemento());
        novoCliente.setEndereco(endereco);

        clienteRepository.save(novoCliente);
        return novoCliente;
    }

    public List<Cliente> listarTodosOsClientes() throws Exception {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()){
            throw new Exception("Nenhum Cliente cadastrado!");
        }
        for (Cliente cliente : clientes) {
            Link selfLink = linkTo(ClienteController.class).slash("burcarPorDocumento").slash(cliente.getNumeroDoDocumento()).withSelfRel();
            cliente.add(selfLink);
        }

        return clientes;
    }

    public Cliente buscarClientePorNumeroDoDocumento(String numeroDoDocumento) throws Exception {
        Cliente clienteExiste = buscarPorDocumento(numeroDoDocumento);
        clienteExiste.add(linkTo(methodOn(ClienteController.class).listarTodosOsClientes()).withRel("Lista de Clientes"));
        return clienteExiste;
    }

    public Cliente atualizarCliente(ClienteRequest clienteRequest) {
        Cliente clienteInDb = buscarPorDocumento(clienteRequest.getNumeroDoDocumento());
        clienteInDb.setNumeroDoDocumento(clienteRequest.getNumeroDoDocumento());
        clienteInDb.setNome(clienteRequest.getNome());
        clienteInDb.setEndereco(buscarCep(clienteRequest));
        clienteRepository.save(clienteInDb);
        return clienteInDb;

    }

    public void deletarCliente(String numeroDoDocumento) {
        clienteRepository.deleteById(buscarPorDocumento(numeroDoDocumento).getId());
    }

    private Endereco buscarCep(ClienteRequest clienteRequest) {
        Endereco novoEndereco = viaCep.buscarEndereco(clienteRequest.getCep());
        novoEndereco.setComplemento(clienteRequest.getComplemento());
        enderecoRepository.save(novoEndereco);
        return novoEndereco;
    }

    private Cliente buscarPorDocumento(String numeroDoDocumento) {
        Optional<Cliente> buscarCliente = clienteRepository.findByNumeroDoDocumento(numeroDoDocumento);
        if (buscarCliente.isEmpty()){
            throw new RuntimeException("Cliente não localizado!");
        }
        return buscarCliente.get();
    }
}


