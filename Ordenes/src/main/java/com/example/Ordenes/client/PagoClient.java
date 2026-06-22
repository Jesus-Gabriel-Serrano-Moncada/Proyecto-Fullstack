package com.example.Ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pago", url = "http://localhost:8085")
public interface PagoClient {

    @GetMapping("/pagos/{id}")
    Object buscarPagoId(@PathVariable long id);
}
