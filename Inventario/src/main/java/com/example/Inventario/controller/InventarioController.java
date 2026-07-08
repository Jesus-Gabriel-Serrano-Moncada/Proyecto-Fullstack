package com.example.Inventario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Inventario.model.Inventario;
import com.example.Inventario.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        Inventario inventario = service.buscarPorId(id);

        if (inventario == null) {
            return ResponseEntity.status(404)
                    .body("Inventario no encontrado");
        }

        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> buscarPorProducto(@PathVariable String productoId) {

        Inventario inventario = service.buscarPorProducto(productoId);

        if (inventario == null) {
            return ResponseEntity.status(404)
                    .body("Inventario no encontrado");
        }

        return ResponseEntity.ok(inventario);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Inventario inventario) {
        return service.crearInventario(inventario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Inventario inventario) {
        return service.actualizarInventario(id, inventario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return service.eliminarInventario(id);
    }

    @PutMapping("/descontar/{productoId}/{cantidad}")
    public ResponseEntity<?> descontar(@PathVariable String productoId,
                                       @PathVariable Integer cantidad) {
        return service.descontarStock(productoId, cantidad);
    }

    @PutMapping("/reponer/{productoId}/{cantidad}")
    public ResponseEntity<?> reponer(@PathVariable String productoId,
                                     @PathVariable Integer cantidad) {
        return service.reponerStock(productoId, cantidad);
    }

}