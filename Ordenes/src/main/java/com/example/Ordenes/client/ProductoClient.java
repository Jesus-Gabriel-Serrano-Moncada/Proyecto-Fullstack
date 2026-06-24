package com.example.Ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "producto", url = "http://localhost:8082")
public interface ProductoClient {

    @GetMapping("/productos/{id}")
    String buscarProducto(@PathVariable String id);
}