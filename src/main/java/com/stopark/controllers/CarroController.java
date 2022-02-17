package com.stopark.controllers;

import com.stopark.models.entities.Carro;
import com.stopark.models.request.CarroRequest;
import com.stopark.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/carro")

public class CarroController {

    @Autowired
    CarroService carroService;

    @PostMapping("/novo")
    @ResponseBody
    public CarroRequest novoCarro(@Valid @RequestBody CarroRequest carroRequest) throws Exception {
    return carroService.adicionarCarro(carroRequest);
    }

    @GetMapping("/procurar")
    public Iterable<Carro> litasTodosOsCarros() { return carroService.procurarTodosOsCarros();}

    @GetMapping("/procurar/{placa}")
    public Optional<Carro> buscarCarroPorPlaca(String placa) {
        return carroService.buscarCarroPelaPlaca(placa);
    }
    
}
