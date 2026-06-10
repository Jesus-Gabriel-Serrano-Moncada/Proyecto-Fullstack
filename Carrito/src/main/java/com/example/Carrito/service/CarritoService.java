package com.example.Carrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Carrito.repository.CarritoRepository;
import com.example.Carrito.model.Carrito;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository CarritoRepository;


    public List<Carrito> obtenerTodos() {
        return CarritoRepository.findAll();
    }


    public Optional<Carrito> obtenerPorId(Long id) {
        return CarritoRepository.findById(id);
    }


    public Carrito guardar(Carrito carrito) {
        return CarritoRepository.save(carrito);
    }

    public void eliminar(Long id) {
        CarritoRepository.deleteById(id);
    }
}