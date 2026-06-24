package com.example.mongoProducto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mongoProducto.model.Producto;
import com.example.mongoProducto.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Producto producto = service.buscarPorId(id);

    if (producto == null) {
        return ResponseEntity.status(404)
                .body("Producto no encontrado con id: " + id);
    }

    return ResponseEntity.ok(producto);
}

    @GetMapping
    public List<Producto> listarProductos() {
        return service.listar();
    }

    @GetMapping("/nombre/{nombre}")
    public List<Producto> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        return service.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String id,
                                               @RequestBody Producto producto) {
        return service.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String id) {
        return service.borrarPorId(id);
    }
}