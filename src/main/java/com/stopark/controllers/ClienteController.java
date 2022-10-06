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
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/cliente")


public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/listar")
    public List<Cliente> listarTodosOsClientes() throws Exception {
    return clienteService.listarTodosOsClientes();}

    @GetMapping(path = "/burcarPorDocumento/{numeroDoDocumento}")
    public Cliente buscarPeloDocumento(@PathVariable String numeroDoDocumento) throws Exception {
        return clienteService.buscarClientePorNumeroDoDocumento(numeroDoDocumento);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClienteRequest> atualizarCliente(@RequestBody ClienteRequest clienteRequest) throws Exception {
        clienteService.atualizarCliente(clienteRequest);
        return ResponseEntity.ok(clienteRequest);
    }


    @PostMapping(value = "/novo")
    @ResponseBody
    public Cliente salvarCliente(@Valid @RequestBody ClienteRequest clienteRequest) throws Exception {
        return clienteService.adicionarCliente(clienteRequest);
    }

    @DeleteMapping(path = "/{numeroDoDocumento}")
    public void deletarCliente(@PathVariable String numeroDoDocumento) {
        clienteService.deletarCliente(numeroDoDocumento);
    }







}


