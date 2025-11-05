package com.multipedidos.componente_a.controller;

import com.multipedidos.componente_a.model.Cliente;
import com.multipedidos.componente_a.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")  
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crearCliente(cliente), HttpStatus.CREATED);
    }

  
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return new ResponseEntity<>(clienteService.obtenerTodosClientes(), HttpStatus.OK);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
