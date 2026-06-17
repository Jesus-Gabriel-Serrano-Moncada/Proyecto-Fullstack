package com.example.Carrito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private String usuarioId;
    private String productoId;

    public Carrito() {}

    public Long getId() {
        return id;
    }
    public String getUsuarioId() {
        return usuarioId;
    }
    public int getCantidad() {
        return cantidad;
    }
    public String getProductoId() {
        return productoId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }
}