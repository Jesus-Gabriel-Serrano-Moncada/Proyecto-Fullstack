package com.example.Ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "http://localhost:8081")
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    Object buscarUsuario(@PathVariable String id);
}
