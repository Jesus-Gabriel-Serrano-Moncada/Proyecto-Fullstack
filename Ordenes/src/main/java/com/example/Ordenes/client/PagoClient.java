package com.example.Ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pagos-service",
        url = "${pago.service.url}"
)
public interface PagoClient {

    @GetMapping("/pagos/{id}")
    String buscarPago(@PathVariable Long id);

}