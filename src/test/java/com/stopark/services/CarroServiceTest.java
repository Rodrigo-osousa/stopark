package com.stopark.services;

import com.stopark.models.entities.Carro;
import com.stopark.models.request.CarroRequest;
import com.stopark.models.request.ClienteRequest;
import com.stopark.repository.CarroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarroServiceTest {

    @Autowired
    CarroRepository carroRepository;

    @Autowired
    CarroService carroService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ViaCepService viaCepService;

    @BeforeAll
    void setUp() throws Exception {
        ClienteRequest novoCliente = new ClienteRequest(5, "333333333", "Nome Teste", viaCepService.buscarEndereco("04840110"));
        clienteService.adicionarCliente(novoCliente);

        CarroRequest novoCarroStp = new CarroRequest();
        novoCarroStp.setPlaca("Test1");
        novoCarroStp.setModeloDoCarro("Modelo Teste");
        novoCarroStp.setMarca("marca1");
        novoCarroStp.setNumeroDoDocumento("333333333");
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
        Date data1 = new Date(03 / 05 / 2021);
        novoCarroStp.setFabricacao(data1);
        carroService.adicionarCarro(novoCarroStp);

        CarroRequest novoCarroStp1 = new CarroRequest();
        novoCarroStp1.setPlaca("Test2");
        novoCarroStp1.setModeloDoCarro("Modelo Teste");
        novoCarroStp1.setMarca("marca1");
        novoCarroStp1.setNumeroDoDocumento("333333333");
        SimpleDateFormat formatador2 = new SimpleDateFormat("yyyy");
        Date data3 = new Date(03 / 05 / 2021);
        novoCarroStp1.setFabricacao(data3);
        carroService.adicionarCarro(novoCarroStp1);

    }

    @Test
    void adicionarCarro() throws Exception {
        CarroRequest novoCarro = new CarroRequest();
        novoCarro.setPlaca("Test3");
        novoCarro.setModeloDoCarro("Modelo Teste");
        novoCarro.setMarca("marca");
        novoCarro.setNumeroDoDocumento("333333333");
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
        Date data2 = new Date(03 / 05 / 2021);
        novoCarro.setFabricacao(data2);
        carroService.adicionarCarro(novoCarro);

        Optional<Carro> adicionouNovoCarro = carroRepository.findByPlaca("Test3");
        Assertions.assertEquals(novoCarro.getPlaca(), adicionouNovoCarro.get().getPlaca());

    }

    @Test
    void procurarTodosOsCarros() {
        List<Carro> buscarTodosOsCarrosTeste = (List<Carro>) carroRepository.findAll();
        Assertions.assertTrue(buscarTodosOsCarrosTeste.size() >= 1);
    }

    @Test
    void buscarCarroPelaPlaca() {
        Optional<Carro> buscarCarroPelaPlaca = carroRepository.findByPlaca("Test1");
        assertEquals("Modelo Teste", buscarCarroPelaPlaca.get().getModeloDoCarro());
    }

    @Test
    void deletarCarro() {
        carroService.deletarCarro("Test2");
        Optional<Carro> buscarCarroPelaPlaca = carroRepository.findByPlaca("Test2");
        Assertions.assertTrue(buscarCarroPelaPlaca.isEmpty());

    }
}