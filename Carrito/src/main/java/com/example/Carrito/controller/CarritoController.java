package com.example.Carrito.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import com.example.Carrito.model.Carrito;
import com.example.Carrito.service.CarritoService;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Carrito> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Carrito> buscar(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Carrito guardar(@RequestBody Carrito carrito) {
        return service.guardar(carrito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}