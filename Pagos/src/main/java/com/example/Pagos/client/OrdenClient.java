package com.example.Pagos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ordenes", url = "http://localhost:8084")
public interface OrdenClient {

    @GetMapping("/ordenes/{id}")
    Object buscarOrden(@PathVariable Long id);
}
