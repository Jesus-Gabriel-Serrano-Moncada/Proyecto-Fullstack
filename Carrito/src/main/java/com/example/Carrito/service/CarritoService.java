package com.example.Carrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Carrito.repository.CarritoRepository;
import com.example.Carrito.model.Carrito;
import com.example.Carrito.service.CarritoService;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository CarritoRepository;

    // Listar todos los carritos
    public List<Carrito> obtenerTodos() {
        return CarritoRepository.findAll();
    }

    // Buscar carrito por ID
    public Optional<Carrito> obtenerPorId(Long id) {
        return CarritoRepository.findById(id);
    }

    // Crear carrito
    public Carrito guardar(Carrito carrito) {
        return CarritoRepository.save(carrito);
    }

    // Eliminar carrito
    public void eliminar(Long id) {
        CarritoRepository.deleteById(id);
    }
}