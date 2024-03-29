package com.stopark.services;

import com.stopark.models.entities.Cliente;
import com.stopark.models.request.ClienteRequest;
import com.stopark.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ClienteServiceTest {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ViaCepRetryService viaCepRetryService;




    @BeforeAll
    void setUp() throws Exception {
        ClienteRequest novoCliente = new ClienteRequest("999999999","Nome Teste","04840110","teste");
        clienteService.adicionarCliente(novoCliente);
        ClienteRequest novoCliente2 = new ClienteRequest("888888888","Nome Teste2","04840110","teste");
        clienteService.adicionarCliente(novoCliente2);
    }

    @Test
    void adicionarCliente() throws Exception {
        ClienteRequest novoCliente = new ClienteRequest();
        novoCliente.setNome("Teste de Criação de novo Cliente");
        novoCliente.setNumeroDoDocumento("123456");
        novoCliente.setCep("04840110");

        Cliente armazenarCliente = clienteService.adicionarCliente(novoCliente);

        Optional<Cliente> possoAdicionarCliente = clienteRepository.findByNumeroDoDocumento("123456");
        Assertions.assertEquals(novoCliente.getNome(), possoAdicionarCliente.get().getNome());
        Assertions.assertEquals(armazenarCliente.getEndereco().getBairro(), possoAdicionarCliente.get().getEndereco().getBairro());
    }
    @Test
    void adicionarClienteExistenteException() throws Exception {
        ClienteRequest novoCliente = new ClienteRequest("999999999","Nome Teste","04840110","teste");
        novoCliente.setCep("04840110");

        Assertions.assertThrows(Exception.class, () -> {
            clienteService.adicionarCliente(novoCliente);
        });

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
        atualizarCliente.setNome("Nome Alterado");
        atualizarCliente.setNumeroDoDocumento("999999999");
        atualizarCliente.setCep("04840110");

        clienteService.atualizarCliente(atualizarCliente);

        Optional<Cliente> possoAtualizarCliente = clienteRepository.findById(1);
        Assertions.assertSame("Nome Alterado", possoAtualizarCliente.get().getNome());

    }

    @Test
    void deletarCliente() {
    clienteService.deletarCliente("888888888");
    Optional<Cliente> possoDeletarCliente = clienteRepository.findByNumeroDoDocumento("888888888");
    Assertions.assertTrue(possoDeletarCliente.isEmpty());
    }
}