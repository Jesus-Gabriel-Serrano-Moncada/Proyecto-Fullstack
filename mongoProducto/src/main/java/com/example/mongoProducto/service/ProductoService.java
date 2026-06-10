package com.example.mongoProducto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.mongoProducto.model.Producto;
import com.example.mongoProducto.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public ResponseEntity<?> crearProducto(Producto p) {

        /* Validar nombre */
        if (p.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede estar vacio!");
        }
        if (p.getNombre().length() < 3) {
            return ResponseEntity.badRequest()
                                    .body("El nombre tiene que tener almenos 3 caracteres!\nNombre actual: ("+p.getNombre()+") - ("+p.getNombre().length()+" Caracteres)");
        }
        if (p.getNombre().length() > 150) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede tener más de 150 caracteres!\nNombre actual: ("+p.getNombre()+") - ("+p.getNombre().length()+" Caracteres)");
        } 

        /* Validacion descripcion */
        if (p.getDescripcion().length() > 200) {
            return ResponseEntity.badRequest()
                                    .body("La descripcion no puede tener mas de 200 caracteres!\nDescripcion actual: ("+p.getDescripcion().length()+" Caracteres)");
        } 
        
        /* Validacion precio */
        if (p.getPrecio() < 1) {
            return ResponseEntity.badRequest()
                                    .body("EL precio tiene que ser mayor a 0!\nPrecio actual: ("+p.getPrecio()+")");
        }

        /* Validacion Stock */
        if (p.getStock() < 1) {
            return ResponseEntity.badRequest()
                                    .body("El stock debe ser mayor a 0!\nStock actual: ("+p.getStock()+")");
        }

        /* Validacion categoria */ 
        if (p.getCategoria().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede estar vacio!");
        }

        /* ELSE */
        return ResponseEntity.ok(repo.save(p));
    }

    public ResponseEntity<?> actualizarProducto(String id, Producto p) {
        
        /* Existe? */
        if (repo.findById(id).orElse(null) == null) {
            return ResponseEntity.status(404)
                                    .body("El producto de id -< "+p.getId()+" >- NO existe");
        }

        /* Validar nombre */
        if (p.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede estar vacio!");
        }
        if (p.getNombre().length() < 3) {
            return ResponseEntity.badRequest()
                                    .body("El nombre tiene que tener almenos 3 caracteres!\nNombre actual: ("+p.getNombre()+") - ("+p.getNombre().length()+" Caracteres)");
        }
        if (p.getNombre().length() > 150) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede tener más de 150 caracteres!\nNombre actual: ("+p.getNombre()+") - ("+p.getNombre().length()+" Caracteres)");
        } 

        /* Validacion descripcion */
        if (p.getDescripcion().length() > 200) {
            return ResponseEntity.badRequest()
                                    .body("La descripcion no puede tener mas de 200 caracteres!\nDescripcion actual: ("+p.getDescripcion().length()+" Caracteres)");
        } 
        
        /* Validacion precio */
        if (p.getPrecio() < 1) {
            return ResponseEntity.badRequest()
                                    .body("EL precio tiene que ser mayor a 0!\nPrecio actual: ("+p.getPrecio()+")");
        }

        /* Validacion Stock */
        if (p.getStock() < 1) {
            return ResponseEntity.badRequest()
                                    .body("El stock debe ser mayor a 0!\nStock actual: ("+p.getStock()+")");
        }

        /* Validacion categoria */ 
        if (p.getCategoria().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede estar vacio!");
        }

        /* ELSE */
        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }

    public ResponseEntity<?> borrarPorId(String id) {

         /* Existe? */
        if (repo.findById(id).orElse(null) == null) {
            return ResponseEntity.status(404)
                                    .body("El producto de id -< "+id+" >- NO existe");
        }
        repo.deleteById(id);
        /* ELSE */
        return ResponseEntity.ok("Producto de id: ("+id+") Ha sido borrado!");
    }
}