package com.stopark.controllers;


import com.stopark.models.entities.Cliente;
import com.stopark.models.request.ClienteRequest;
import com.stopark.services.ClienteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")


public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/listar")
    public Iterable<Cliente> listarTodosOsClientes() {
    return clienteService.listarTodosOsClientes();}

    @GetMapping("/burcarPorDocumento")
    public Optional<Cliente> buscarPeloDocumento(String numeroDoDocumento) {
        return clienteService.buscarClientePorNumeroDoDocumento(numeroDoDocumento);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClienteRequest> atualizarCliente(@RequestBody ClienteRequest clienteRequest) throws Exception {
        clienteService.atualizarCliente(clienteRequest);
        return ResponseEntity.ok(clienteRequest);
    }


    @PostMapping(value = "/novo")
    @ResponseBody
    public ResponseEntity<ClienteRequest> salvarCliente(@Valid @RequestBody ClienteRequest clienteRequest) throws Exception {
        clienteService.adicionarCliente(clienteRequest);
    return ResponseEntity.ok(clienteRequest);

    }

    @DeleteMapping(path = "/{id}")
    public void deletarCliente(@PathVariable int id) {
        clienteService.deletarCliente(id);
    }







}


