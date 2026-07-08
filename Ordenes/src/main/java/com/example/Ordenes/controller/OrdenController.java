package com.example.Ordenes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Ordenes.model.Ordenes;
import com.example.Ordenes.service.OrdenService;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Ordenes> buscarPorUsuario(@PathVariable String usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

    Ordenes orden = service.buscarPorId(id);

    if (orden == null) {
        return ResponseEntity.status(404)
                .body("La orden con id < " + id + " > no existe");
    }

    return ResponseEntity.ok(orden);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Ordenes o) {
        return service.crearOrden(o);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Ordenes o) {
        return service.actualizarOrden(id, o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return service.borrarPorId(id);
    }
}