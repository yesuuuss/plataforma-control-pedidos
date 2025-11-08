package com.multipedidos.componente_a.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Producto {
    private String nombre;
    private Double precio;
    
    
    public Producto() {}
    
    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}