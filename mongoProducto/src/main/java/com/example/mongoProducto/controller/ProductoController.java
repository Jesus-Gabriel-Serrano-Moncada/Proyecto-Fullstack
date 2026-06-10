package com.example.mongoProducto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mongoProducto.model.Producto;
import com.example.mongoProducto.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listProductos() {
        return service.listar();
    }

    @GetMapping("/nombre/{nombre}")
    public List<Producto> searchByNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto p) {
        return service.crearProducto(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable String id, @RequestBody Producto p) {
        return service.actualizarProducto(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable String id) {
        return service.borrarPorId(id);
    }
}