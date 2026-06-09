package com.example.UsuarioMongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.UsuarioMongo.model.Usuario;
import com.example.UsuarioMongo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> list() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Usuario u) {
        return service.crear(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody Usuario u) {
        return service.actualizar(id, u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        return service.borrarPorId(id);
    }
}
