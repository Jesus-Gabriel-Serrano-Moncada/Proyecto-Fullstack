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
                                    .body("El nombre no puede estar vacio!\nNombre actual: ("+p.getNombre()+")");
        }
        if (p.getNombre().length() < 3) {
            return ResponseEntity.badRequest()
                                    .body("El nombre tiene que tener almenos 3 caracteres!\nNombre actual: ("+p.getNombre()+") - ("+.p.getNombre().length()+" Caracteres)");
        }
        if (p.getNombre().length() > 50) {
            return ResponseEntity.badRequest()
                                    .body("El nombre no puede estar vacio!\nNombre actual: ("+p.getNombre()+") - ("+p.getNombre().length()+" Caracteres)");
        } 

        /* Validar descripcion */

    }
}
