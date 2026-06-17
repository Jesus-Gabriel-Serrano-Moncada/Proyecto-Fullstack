package com.example.Ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pago", url = "http://localhost:8085")
public interface PagoClient {
    
    @GetMapping
    Object buscarPagoId(@PathVariable long id);
}
