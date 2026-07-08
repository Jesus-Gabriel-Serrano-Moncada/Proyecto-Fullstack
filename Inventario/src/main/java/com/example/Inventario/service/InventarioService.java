package com.example.Inventario.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Inventario.client.ProductoClient;
import com.example.Inventario.model.Inventario;
import com.example.Inventario.repository.InventarioRepository;

@Service
public class InventarioService {

    private final InventarioRepository repo;
    private final ProductoClient productoClient; // <-- AGREGAR

    public InventarioService(
            InventarioRepository repo,
            ProductoClient productoClient) {

        this.repo = repo;
        this.productoClient = productoClient;
    }

    public List<Inventario> listar() {
        return repo.findAll();
    }

    public Inventario buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Inventario buscarPorProducto(String productoId) {
        return repo.findByProductoId(productoId).orElse(null);
    }

    public ResponseEntity<?> crearInventario(Inventario inventario) {

        try {

            String producto = productoClient.buscarProducto(inventario.getProductoId());

            if (producto == null) {
                return ResponseEntity.badRequest()
                        .body("El producto no existe");
            }

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("El producto no existe o el microservicio no está disponible");
        }

        String validacion = validarInventario(inventario);

        if (validacion != null) {
            return ResponseEntity.badRequest().body(validacion);
        }

        return ResponseEntity.ok(repo.save(inventario));
    }

    public ResponseEntity<?> actualizarInventario(Long id, Inventario inventario) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("El inventario con id < " + id + " > no existe");
        }

        // Verificar que exista el producto
        try {

            String producto = productoClient.buscarProducto(inventario.getProductoId());

            if (producto == null) {
                return ResponseEntity.badRequest()
                        .body("El producto no existe");
            }

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("El producto no existe o el microservicio no está disponible");
        }

        String validacion = validarInventario(inventario);

        if (validacion != null) {
            return ResponseEntity.badRequest().body(validacion);
        }

        inventario.setId(id);

        return ResponseEntity.ok(repo.save(inventario));
    }

    public ResponseEntity<?> eliminarInventario(Long id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("El inventario con id < " + id + " > no existe");
        }

        repo.deleteById(id);

        return ResponseEntity.ok("Inventario eliminado correctamente");
    }

    public ResponseEntity<?> descontarStock(String productoId, Integer cantidad) {

        Inventario inventario = repo.findByProductoId(productoId).orElse(null);

        if (inventario == null) {
            return ResponseEntity.badRequest()
                    .body("No existe inventario para este producto");
        }

        if (inventario.getStockDisponible() < cantidad) {
            return ResponseEntity.badRequest()
                    .body("Stock insuficiente");
        }

        inventario.setStockDisponible(
                inventario.getStockDisponible() - cantidad);

        repo.save(inventario);

        return ResponseEntity.ok(inventario);
    }

    public ResponseEntity<?> reponerStock(String productoId, Integer cantidad) {

        Inventario inventario = repo.findByProductoId(productoId).orElse(null);

        if (inventario == null) {
            return ResponseEntity.badRequest()
                    .body("No existe inventario para este producto");
        }

        inventario.setStockDisponible(
                inventario.getStockDisponible() + cantidad);

        repo.save(inventario);

        return ResponseEntity.ok(inventario);
    }

    private String validarInventario(Inventario inventario) {

        if (inventario == null)
            return "El inventario no puede ser nulo";

        if (inventario.getProductoId() == null ||
            inventario.getProductoId().trim().isEmpty())
            return "Debe indicar el producto";

        if (inventario.getStockDisponible() == null ||
            inventario.getStockDisponible() < 0)
            return "El stock disponible debe ser mayor o igual a 0";

        if (inventario.getStockMinimo() == null ||
            inventario.getStockMinimo() < 0)
            return "El stock mínimo debe ser mayor o igual a 0";

        if (inventario.getUbicacion() == null ||
            inventario.getUbicacion().trim().isEmpty())
            return "Debe indicar una ubicación";

        return null;
    }

}