package com.example.Notificaciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "usuario",
        url = "http://localhost:8081"
)
public interface UsuarioClient {


    @GetMapping("/usuarios/{id}")
    String buscarUsuario(@PathVariable String id);

}