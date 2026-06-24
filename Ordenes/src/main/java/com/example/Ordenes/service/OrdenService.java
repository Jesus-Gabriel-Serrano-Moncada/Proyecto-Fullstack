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

        try {
            System.out.println("Llamando UsuarioClient...");
            String usuario = usuarioClient.buscarUsuario(o.getUsuarioId());
            System.out.println("Usuario respuesta: " + usuario);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("ERROR FEIGN USUARIO: " + e.getMessage());
        }

        Object producto;

        try {
            System.out.println("➡ Llamando ProductoClient...");
            producto = productoClient.buscarProducto(o.getProductoId());
            System.out.println("✔ Producto respuesta: " + producto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("ERROR FEIGN PRODUCTO: " + e.getMessage());
        }

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

        // CALCULO DEL TOTAL
        Map<String, Object> prodMap = (Map<String, Object>) producto;

        Float precio = Float.parseFloat(prodMap.get("precio").toString());
        Float total = precio * o.getCantidad();

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