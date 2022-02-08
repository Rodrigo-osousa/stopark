package com.stopark.controllers;

import com.stopark.models.request.CarroRequest;
import com.stopark.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carro")

public class CarroController {

    @Autowired
    CarroService carroService;

    @PostMapping("/novo")
    @ResponseBody
    public CarroRequest novoCarro(@Valid @RequestBody CarroRequest carroRequest) {
    return carroService.adicionarCarro(carroRequest);
    }
    
}
