package com.example.mongoProducto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Producto {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private float precio;
    private int stock;
    private String categoria;

    public Producto() {}

    /* GETTERS */
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public float getPrecio() {
        return precio;
    }
    public String getCategoria() {
        return categoria;
    }
    public int getStock() {
        return stock;
    }

    /* SETTERS */
    public void setId(String id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
