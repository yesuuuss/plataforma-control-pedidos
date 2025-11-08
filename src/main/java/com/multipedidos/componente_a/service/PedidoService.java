package com.multipedidos.componente_a.service;

import com.multipedidos.componente_a.model.Pedido;
import com.multipedidos.componente_a.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido crearPedido(Pedido pedido) {
       
        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}