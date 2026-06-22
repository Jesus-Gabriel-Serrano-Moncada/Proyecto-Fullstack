package com.example.Pagos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Pagos.model.Pago;
import com.example.Pagos.service.PagoService;


@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService service;

    @GetMapping
    public List<Pago>listPagos(){
        return service.listar();
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {

        List<Pago> pagos = service.buscarPorEstado(estado);

        if (pagos.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No existen pagos con estado: " + estado);
        }

        return ResponseEntity.ok(pagos);
    }
    
    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody Pago p){
        return service.crearPago(p);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(@PathVariable Long id, @RequestBody Pago p){
        return service.actualizarPago(id, p);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Long id){
        return service.borrarPorId(id);
    } 
}
