package com.example.Notificaciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.service.NotificacionService;


@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {


    private final NotificacionService service;


    public NotificacionController(NotificacionService service) {

        this.service = service;
    }



    @GetMapping
    public ResponseEntity<?> listar() {

        return service.listar();
    }



    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(
            @PathVariable String usuarioId) {

        List<Notificacion> lista =
                service.buscarPorUsuario(usuarioId);

        return ResponseEntity.ok(lista);
    }



    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<?> buscarPorOrden(
            @PathVariable Long ordenId) {

        List<Notificacion> lista =
                service.buscarPorOrden(ordenId);

        return ResponseEntity.ok(lista);
    }



    @PostMapping
    public ResponseEntity<?> crear(
            @RequestBody Notificacion notificacion) {

        return service.crearNotificacion(notificacion);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Notificacion notificacion) {

        return service.actualizarNotificacion(id, notificacion);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long id) {

        return service.eliminarNotificacion(id);
    }

}