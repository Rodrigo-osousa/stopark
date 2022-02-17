package com.stopark.services;

import com.stopark.models.entities.Cliente;
import com.stopark.models.request.ClienteRequest;
import com.stopark.repository.ClienteRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ClienteServiceTest {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ViaCepService viaCepService;

    @BeforeAll
    void setUp() throws Exception {
        ClienteRequest novoCliente = new ClienteRequest(1,"999999999","Nome Teste",viaCepService.buscarEndereco("04840110"));
        clienteService.adicionarCliente(novoCliente);
        ClienteRequest novoCliente2 = new ClienteRequest(2,"888888888","Nome Teste2",viaCepService.buscarEndereco("04840110"));
        clienteService.adicionarCliente(novoCliente2);
    }

    @Test
    void adicionarCliente() throws Exception {
        ClienteRequest novoCliente = new ClienteRequest();
        novoCliente.setNome("Teste de Criação de novo Cliente");
        novoCliente.setNumeroDoDocumento("123456");
        novoCliente.setEndereco(viaCepService.buscarEndereco("04840110"));
        clienteService.adicionarCliente(novoCliente);

        Optional<Cliente> possoAdicionarCliente = clienteRepository.findByNumeroDoDocumento("123456");
        Assertions.assertEquals(novoCliente.getNome(), possoAdicionarCliente.get().getNome());
    }

    @Test
    void listarTodosOsClientes() {
        List<Cliente> quantosClientesTem = (List<Cliente>) clienteRepository.findAll();
        Assertions.assertTrue(quantosClientesTem.size() >= 1);
    }

    @Test
    void buscarClientePorNumeroDoDocumento() {
        Optional<Cliente> possoBuscarClientePeloDocumento = clienteRepository.findByNumeroDoDocumento("888888888");
        Assertions.assertSame("Nome Teste2", possoBuscarClientePeloDocumento.get().getNome());
    }

    @Test
    void atualizarCliente() throws Exception {
        ClienteRequest atualizarCliente = new ClienteRequest();
        atualizarCliente.setId(1);
        atualizarCliente.setNome("Nome Alterado");
        atualizarCliente.setNumeroDoDocumento("999999999");
        atualizarCliente.setEndereco(viaCepService.buscarEndereco("04840110"));

        clienteService.atualizarCliente(atualizarCliente);

        Optional<Cliente> possoAtualizarCliente = clienteRepository.findById(1);
        Assertions.assertSame("Nome Alterado", possoAtualizarCliente.get().getNome());

    }

    @Test
    void deletarCliente() {
    clienteService.deletarCliente(2);
    Optional<Cliente> possoDeletarCliente = clienteRepository.findByNumeroDoDocumento("888888888");
    Assertions.assertTrue(possoDeletarCliente.isEmpty());
    }
}