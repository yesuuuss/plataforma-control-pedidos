package com.multipedidos.componente_a.service;

import com.multipedidos.componente_a.model.Cliente;
import com.multipedidos.componente_a.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

   
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }


    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
}
