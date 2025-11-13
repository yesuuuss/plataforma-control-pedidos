package com.multipedidos.componente_a.controller;

import com.multipedidos.componente_a.model.Pedido;
import com.multipedidos.componente_a.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.crearPedido(pedido), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        return new ResponseEntity<>(pedidoService.obtenerTodosPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(id);
        return pedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   
    @PostMapping("/{id}/descuento")
    public ResponseEntity<?> aplicarDescuento(
        @PathVariable Long id, 
        @RequestParam double porcentajeDescuento) {
    try {
        Pedido pedidoActualizado = pedidoService.aplicarDescuentoAPedido(id, porcentajeDescuento);
        return ResponseEntity.ok(pedidoActualizado);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}