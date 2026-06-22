package com.example.Ordenes.model;

import jakarta.persistence.*;

@Entity
public class Ordenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;
    private String productoId;
    private int cantidad;
    private float total;
    private String estado;

    public Ordenes() {}

    public Ordenes(String usuarioId, String productoId, int cantidad, float total, String estado) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}