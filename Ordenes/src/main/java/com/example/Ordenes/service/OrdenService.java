package com.example.Ordenes.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Ordenes.client.ProductoClient;
import com.example.Ordenes.client.UsuarioClient;
import com.example.Ordenes.model.Ordenes;
import com.example.Ordenes.repository.OrdenRepository;

@Service
public class OrdenService {

    private final OrdenRepository repo;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    public OrdenService(OrdenRepository repo,
                        UsuarioClient usuarioClient,
                        ProductoClient productoClient) {
        this.repo = repo;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    public List<Ordenes> listar() {
        return repo.findAll();
    }

    public List<Ordenes> buscarPorUsuario(String usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public ResponseEntity<?> crearOrden(Ordenes o) {

        // 1. Validar usuario existe
        Object usuario = usuarioClient.buscarUsuario(o.getUsuarioId());
        if (usuario == null) {
            return ResponseEntity.badRequest()
                    .body("Usuario no existe");
        }

        // 2. Validar producto existe
        Object producto = productoClient.buscarProducto(o.getProductoId());
        if (producto == null) {
            return ResponseEntity.badRequest()
                    .body("Producto no existe");
        }

        // 3. Validaciones básicas
        if (o.getCantidad() < 1) {
            return ResponseEntity.badRequest()
                    .body("La cantidad debe ser mayor a 0");
        }

        // 4. Estado por defecto
        if (o.getEstado() == null || o.getEstado().trim().isEmpty()) {
            o.setEstado("PENDIENTE");
        }

        // 5. CALCULO DE TOTAL (IMPORTANTE)
        // Aquí asumimos que Producto tiene "precio"
        try {
            java.util.Map<String, Object> prodMap = (java.util.Map<String, Object>) producto;
            double precio = Double.parseDouble(prodMap.get("precio").toString());

            double total = precio * o.getCantidad();
            o.setTotal((float) total);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al calcular total del producto");
        }

        return ResponseEntity.ok(repo.save(o));
    }

    public ResponseEntity<?> actualizarOrden(Long id, Ordenes o) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("La orden de id < " + id + " > NO existe");
        }

        o.setId(id);
        return ResponseEntity.ok(repo.save(o));
    }

    public ResponseEntity<?> borrarPorId(Long id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("La orden de id < " + id + " > NO existe");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Orden eliminada correctamente");
    }
}