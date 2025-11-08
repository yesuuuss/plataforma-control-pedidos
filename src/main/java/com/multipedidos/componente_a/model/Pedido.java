package com.multipedidos.componente_a.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ElementCollection
    @CollectionTable(name = "pedido_productos", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<Producto> productos = new ArrayList<>();
    
    private Double total;
    
    // Constructores
    public Pedido() {}
    
    public Pedido(Cliente cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
        calcularTotal();
    }
    
   
    public void calcularTotal() {
        if (productos == null || productos.isEmpty()) {
            this.total = 0.0;
            return;
        }
        
        double subtotal = productos.stream()
            .mapToDouble(Producto::getPrecio)
            .sum();
        
       
       this.total = subtotal * 1.12; 
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { 
        this.productos = productos; 
        calcularTotal(); 
    }
    
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}