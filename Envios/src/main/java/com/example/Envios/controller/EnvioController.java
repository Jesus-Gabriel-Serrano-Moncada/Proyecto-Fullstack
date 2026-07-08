package com.example.Envios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Envios.model.Envio;
import com.example.Envios.service.EnvioService;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService service;


    public EnvioController(EnvioService service) {
        this.service = service;
    }


    @GetMapping
    public List<Envio> listarEnvios() {
        return service.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Long id) {

        Envio envio = service.buscarPorId(id);

        if (envio == null) {

            return ResponseEntity.status(404)
                    .body("No existe envío con id: " + id);
        }

        return ResponseEntity.ok(envio);
    }


    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<?> buscarPorOrden(
            @PathVariable Long ordenId) {

        return ResponseEntity.ok(
                service.buscarPorOrden(ordenId));
    }


    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(
            @PathVariable String estado) {

        return ResponseEntity.ok(
                service.buscarPorEstado(estado));
    }


    @PostMapping
    public ResponseEntity<?> crearEnvio(
            @RequestBody Envio envio) {

        return service.crearEnvio(envio);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEnvio(
            @PathVariable Long id,
            @RequestBody Envio envio) {

        return service.actualizarEnvio(id, envio);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEnvio(
            @PathVariable Long id) {

        return service.eliminarEnvio(id);
    }

}