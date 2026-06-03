package com.example.mongoProducto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongoProducto.model.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String>{
    List<Producto> findByNombre(String nombre);
}
