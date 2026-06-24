package com.example.Ordenes.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Ordenes.client.ProductoClient;
import com.example.Ordenes.client.UsuarioClient;
import com.example.Ordenes.model.Ordenes;
import com.example.Ordenes.repository.OrdenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdenService {

    private final OrdenRepository repo;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public OrdenService(
            OrdenRepository repo,
            UsuarioClient usuarioClient,
            ProductoClient productoClient) {

        this.repo = repo;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    public ResponseEntity<?> listar() {
        List<Ordenes> lista = repo.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No hay ordenes registradas actualmente");
        }

        return ResponseEntity.ok(lista);
    }

    public List<Ordenes> buscarPorUsuario(String usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public ResponseEntity<?> crearOrden(Ordenes o) {

        try {
            String usuario = usuarioClient.buscarUsuario(o.getUsuarioId());

            if (usuario == null || usuario.isBlank()) {
                return ResponseEntity.badRequest()
                        .body("El usuario no existe");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("ERROR FEIGN USUARIO: " + e.getMessage());
        }

        String producto;

        try {
            producto = productoClient.buscarProducto(o.getProductoId());

            if (producto == null || producto.isBlank()) {
                return ResponseEntity.badRequest()
                        .body("El producto no existe");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("ERROR FEIGN PRODUCTO: " + e.getMessage());
        }

        if (o.getCantidad() < 1) {
            return ResponseEntity.badRequest()
                    .body("La cantidad debe ser mayor a 0");
        }

        if (o.getEstado() == null || o.getEstado().trim().isEmpty()) {
            o.setEstado("PENDIENTE");
        }

        try {
            Map<String, Object> prodMap =
                    mapper.readValue(producto, Map.class);

            Float precio = Float.parseFloat(prodMap.get("precio").toString());
            Float total = precio * o.getCantidad();

            o.setTotal(total);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error procesando producto: " + e.getMessage());
        }

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