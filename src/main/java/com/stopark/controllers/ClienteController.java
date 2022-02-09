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

    @GetMapping("listar")
    public Iterable<Cliente> listarTodosOsClientes() {
    return clienteService.listarTodosOsClientes();}


    @PostMapping(value = "/novo")
    @ResponseBody
    public ResponseEntity<ClienteRequest> salvarCliente(@Valid @RequestBody ClienteRequest clienteRequest) throws Exception {
        clienteService.adicionarCliente(clienteRequest);
    return ResponseEntity.ok(clienteRequest);

    }





}


