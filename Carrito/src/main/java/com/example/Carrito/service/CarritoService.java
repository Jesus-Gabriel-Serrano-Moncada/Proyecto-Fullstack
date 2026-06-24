package com.example.Carrito.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Carrito.model.Carrito;
import com.example.Carrito.repository.CarritoRepository;

import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository repo;

    public CarritoService(CarritoRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> obtenerTodos() {

        List<Carrito> lista = repo.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No hay productos en el carrito");
        }

        return ResponseEntity.ok(lista);
    }

    public ResponseEntity<?> obtenerPorId(Long id) {

        Carrito c = repo.findById(id).orElse(null);

        if (c == null) {
            return ResponseEntity.status(404)
                    .body("No hay un carrito con la id < " + id + ">");
        }

        return ResponseEntity.ok(c);
    }

    public ResponseEntity<?> guardar(Carrito c) {

        if (c.getUsuarioId() == null || c.getProductoId() == null || c.getCantidad() <= 0) {
            return ResponseEntity.badRequest()
                    .body("Datos inválidos en el carrito");
        }

        return ResponseEntity.ok(repo.save(c));
    }

    public ResponseEntity<?> eliminar(Long id) {

        Carrito c = repo.findById(id).orElse(null);

        if (c == null) {
            return ResponseEntity.status(404)
                    .body("No hay un carrito con la id < " + id + ">");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Carrito < " + id + "> ha sido eliminado");
    }
    
    public ResponseEntity<?> actualizar(Long id, Carrito c) {

    Carrito existente = repo.findById(id).orElse(null);

    if (existente == null) {
        return ResponseEntity.status(404)
                .body("No hay un carrito con la id < " + id + " >");
    }

    if (c.getCantidad() < 1) {
        return ResponseEntity.badRequest()
                .body("La cantidad debe ser mayor a 0");
    }

    c.setId(id);

    return ResponseEntity.ok(repo.save(c));
}
}