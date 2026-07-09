package com.example.Ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "usuario-service",
        url = "${usuario.service.url}"
)
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    String buscarUsuario(@PathVariable String id);

}