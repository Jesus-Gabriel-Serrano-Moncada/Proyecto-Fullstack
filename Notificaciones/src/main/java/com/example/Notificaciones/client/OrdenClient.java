package com.example.Notificaciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "ordenes",
        url = "http://localhost:8084"
)
public interface OrdenClient {


    @GetMapping("/ordenes/{id}")
    String buscarOrden(@PathVariable Long id);

}