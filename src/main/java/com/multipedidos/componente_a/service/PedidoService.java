package com.multipedidos.componente_a.service;

import com.multipedidos.componente_a.model.Pedido;
import com.multipedidos.componente_a.model.Producto;
import com.multipedidos.componente_a.repository.PedidoRepository;
import com.multipedidos.common.OperacionesNegocio; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido crearPedido(Pedido pedido) {
      
        if (pedido.getTotal() == null) {
            double subtotal = calcularSubtotal(pedido.getProductos());
            double totalConIVA = OperacionesNegocio.calcularTotalConIVA(subtotal);
            pedido.setTotal(totalConIVA);
        }
        
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        
       
        pedido.ifPresent(p -> {
            if (p.getTotal() == null) {
                double subtotal = calcularSubtotal(p.getProductos());
                double totalConIVA = OperacionesNegocio.calcularTotalConIVA(subtotal);
                p.setTotal(totalConIVA);
            }
        });
        
        return pedido;
    }

    public List<Pedido> obtenerTodosPedidos() {
        return pedidoRepository.findAll();
    }

    
    public Pedido aplicarDescuentoAPedido(Long pedidoId, double porcentajeDescuento) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        double totalConDescuento = OperacionesNegocio.aplicarDescuento(
            pedido.getTotal(), 
            porcentajeDescuento
        );

        pedido.setTotal(totalConDescuento);
        return pedidoRepository.save(pedido);
    }

    private double calcularSubtotal(List<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            return 0.0;
        }
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
}