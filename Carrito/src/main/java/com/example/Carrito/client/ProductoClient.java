package com.example.Carrito.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "producto-service",
        url = "${producto.service.url}"
)
public interface ProductoClient {

    @GetMapping("/productos/{id}")
    String buscarProducto(@PathVariable String id);

}