package com.example.Ordenes.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Ordenes.model.Ordenes;
import com.example.Ordenes.repository.OrdenRepository;

@Service
public class OrdenService {

    private final OrdenRepository repo;

    public OrdenService(OrdenRepository repo) {
        this.repo = repo;
    }

    public List<Ordenes> listar() {
        return repo.findAll();
    }

    public List<Ordenes> buscarPorUsuario(String usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public ResponseEntity<?> crearOrden(Ordenes o) {

        if (o.getUsuarioId() == null || o.getUsuarioId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no puede estar vacío");
        }

        if (o.getProductoId() == null || o.getProductoId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El producto no puede estar vacío");
        }

        if (o.getCantidad() < 1) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor a 0");
        }

        if (o.getEstado() == null || o.getEstado().trim().isEmpty()) {
            o.setEstado("PENDIENTE");
        }

        return ResponseEntity.ok(repo.save(o));
    }

    public ResponseEntity<?> actualizarOrden(Long id, Ordenes o) {

        if (repo.findById(id).orElse(null) == null) {
            return ResponseEntity.status(404)
                    .body("La orden de id < " + id + " > NO existe");
        }

        o.setId(id);
        return ResponseEntity.ok(repo.save(o));
    }

    public ResponseEntity<?> borrarPorId(Long id) {

        if (repo.findById(id).orElse(null) == null) {
            return ResponseEntity.status(404)
                    .body("La orden de id < " + id + " > NO existe");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Orden eliminada correctamente");
    }
}