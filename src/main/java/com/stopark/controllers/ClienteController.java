package com.stopark.controllers;


import com.stopark.models.entities.Cliente;
import com.stopark.models.request.ClienteRequest;
import com.stopark.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")


public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping(value = "/novo")
    @ResponseBody
    public ResponseEntity<Cliente> salvarCliente(@Valid @RequestBody Cliente cliente) {
        clienteService.adicionarCliente(cliente);
    return ResponseEntity.ok(cliente);

    }





}


