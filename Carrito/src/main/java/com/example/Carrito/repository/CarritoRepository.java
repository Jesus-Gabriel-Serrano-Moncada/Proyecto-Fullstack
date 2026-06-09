package com.example.Carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Carrito.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}