package com.example.Ordenes.service;

import java.util.List;
import java.util.Map;

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

    public OrdenService(
            OrdenRepository repo,
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

        Object usuario = usuarioClient.buscarUsuario(o.getUsuarioId());
        if (usuario == null) {
            return ResponseEntity.badRequest()
                    .body("El usuario no existe");
        }

        Object producto = productoClient.buscarProducto(o.getProductoId());
        if (producto == null) {
            return ResponseEntity.badRequest()
                    .body("El producto no existe");
        }

        if (o.getCantidad() < 1) {
            return ResponseEntity.badRequest()
                    .body("La cantidad debe ser mayor a 0");
        }

        if (o.getEstado() == null || o.getEstado().trim().isEmpty()) {
            o.setEstado("PENDIENTE");
        }

        // Cálculo del total
        Map<String, Object> prodMap = (Map<String, Object>) producto;
        float precio = Float.parseFloat(prodMap.get("precio").toString());
        float total = precio * o.getCantidad();

        o.setTotal(total);

        return ResponseEntity.ok(repo.save(o));
    }

    public ResponseEntity<?> actualizarOrden(Long id, Ordenes o) {

        Ordenes existente = repo.findById(id).orElse(null);

        if (existente == null) {
            return ResponseEntity.status(404)
                    .body("La orden de id < " + id + " > no existe");
        }

        o.setId(id);
        return ResponseEntity.ok(repo.save(o));
    }

    public ResponseEntity<?> borrarPorId(Long id) {

        Ordenes existente = repo.findById(id).orElse(null);

        if (existente == null) {
            return ResponseEntity.status(404)
                    .body("La orden de id < " + id + " > no existe");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Orden eliminada correctamente");
    }
}