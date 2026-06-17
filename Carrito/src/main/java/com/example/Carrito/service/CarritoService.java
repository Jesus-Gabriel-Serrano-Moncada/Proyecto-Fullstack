package com.example.Carrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Carrito.repository.CarritoRepository;

import com.example.Carrito.model.Carrito;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository repo;


    public ResponseEntity<?> obtenerTodos() {

        if (repo.findAll().isEmpty()) {
            return ResponseEntity
                .status(404)
                    .body("No hay productos en el carrito");
        }
        return ResponseEntity
                    .ok(repo.findAll());
    }


    public ResponseEntity<?> obtenerPorId(Long id) {
        
        if (repo.findById(id).orElse(null) == null) {
            return ResponseEntity
                        .status(404)
                            .body("No hay un carrito con la id < "+id+" >");
        }
        return ResponseEntity.ok(repo.findById(id));
    }


    public Carrito guardar(Carrito carrito) {
        return repo.save(carrito);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}