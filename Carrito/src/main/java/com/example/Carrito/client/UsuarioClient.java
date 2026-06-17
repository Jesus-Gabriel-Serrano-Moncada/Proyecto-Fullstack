package com.example.Carrito.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario", url = "http://localhost:8081")
public interface UsuarioClient {
    
    @GetMapping("{id}")
    Object buscarUsuarioId(@PathVariable String id);
}
