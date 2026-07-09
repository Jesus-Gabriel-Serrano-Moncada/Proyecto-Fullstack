package com.example.Envios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ordenes-service",
        url = "${orden.service.url}"
)
public interface OrdenClient {

    @GetMapping("/ordenes/{id}")
    String buscarOrden(@PathVariable Long id);

}