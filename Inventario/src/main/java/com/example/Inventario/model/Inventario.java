package com.example.Inventario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productoId;

    private Integer stockDisponible;

    private Integer stockMinimo;

    private String ubicacion;

    public Inventario() {
    }

    public Inventario(Long id, String productoId, Integer stockDisponible,
                      Integer stockMinimo, String ubicacion) {
        this.id = id;
        this.productoId = productoId;
        this.stockDisponible = stockDisponible;
        this.stockMinimo = stockMinimo;
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return id;
    }

    public String getProductoId() {
        return productoId;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}