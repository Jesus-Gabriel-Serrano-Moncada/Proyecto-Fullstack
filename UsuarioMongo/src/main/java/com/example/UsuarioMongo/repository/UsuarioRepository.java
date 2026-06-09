package com.example.UsuarioMongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.UsuarioMongo.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
}
