package com.example.mongoProducto.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.mongoProducto.model.Producto;
import com.example.mongoProducto.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public ResponseEntity<?> crearProducto(Producto p) {
        String validacion = validarProducto(p);
        if (validacion != null) {
            return ResponseEntity.badRequest().body(validacion);
        }

        return ResponseEntity.ok(repo.save(p));
    }

    public ResponseEntity<?> actualizarProducto(String id, Producto p) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("El producto de id < " + id + " > NO existe");
        }

        String validacion = validarProducto(p);
        if (validacion != null) {
            return ResponseEntity.badRequest().body(validacion);
        }

        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }

    public ResponseEntity<?> borrarPorId(String id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("El producto de id < " + id + " > NO existe");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Producto de id (" + id + ") ha sido borrado");
    }

    private String validarProducto(Producto p) {

        if (p == null) return "El producto no puede ser nulo";

        if (p.getNombre() == null || p.getNombre().trim().isEmpty())
            return "El nombre no puede estar vacío";

        if (p.getNombre().length() < 3)
            return "El nombre debe tener al menos 3 caracteres";

        if (p.getNombre().length() > 150)
            return "El nombre no puede superar 150 caracteres";

        if (p.getDescripcion() != null && p.getDescripcion().length() > 200)
            return "La descripción no puede superar 200 caracteres";

        if (p.getPrecio() < 1)
            return "El precio debe ser mayor a 0";

        if (p.getStock() < 1)
            return "El stock debe ser mayor a 0";

        if (p.getCategoria() == null || p.getCategoria().trim().isEmpty())
            return "La categoría no puede estar vacía";

        return null;
    }
}