package com.example.mongoProducto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {

    @Id
    private String id;

    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer stock;
    private String categoria;

    public Producto() {}

    public Producto(String id, String nombre, String descripcion, Float precio, Integer stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    /* GETTERS */
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Float getPrecio() { return precio; }
    public Integer getStock() { return stock; }
    public String getCategoria() { return categoria; }

    /* SETTERS */
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(Float precio) { this.precio = precio; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}