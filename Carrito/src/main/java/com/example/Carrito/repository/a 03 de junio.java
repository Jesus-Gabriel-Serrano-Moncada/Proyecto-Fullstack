package com example

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CarritoService.model.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<Carrito, Long> {

}