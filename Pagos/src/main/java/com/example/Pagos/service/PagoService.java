package com.example.Pagos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Pagos.model.Pago;
import com.example.Pagos.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository repo;

    public List<Pago> listar() {
        return repo.findAll();
    }

    public List<Pago> buscarPorEstado(String estado) {
        return repo.findByEstado(estado);
    }

    public ResponseEntity<?> crearPago(Pago p) {

        if (p.getOrdenId() == null) {
            return ResponseEntity.badRequest()
                    .body("Debe indicar una orden");
        }

        if (p.getMetodoPago() == null || p.getMetodoPago().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Debe indicar un metodo de pago");
        }

        if (p.getMonto() < 1) {
            return ResponseEntity.badRequest()
                    .body("El monto debe ser mayor a 0");
        }

        if (p.getEstado() == null || p.getEstado().trim().isEmpty()) {
            p.setEstado("PENDIENTE");
        }

        return ResponseEntity.ok(repo.save(p));
    }

    public ResponseEntity<?> actualizarPago(Long id, Pago p) {

        Pago existente = repo.findById(id).orElse(null);

        if (existente == null) {
            return ResponseEntity.status(404)
                    .body("El pago de id " + id + " no existe");
        }

        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }

    public ResponseEntity<?> borrarPorId(Long id) {

        Pago existente = repo.findById(id).orElse(null);

        if (existente == null) {
            return ResponseEntity.status(404)
                    .body("El pago de id " + id + " no existe");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("El pago del id: " + id + " ha sido eliminado correctamente");
    }
}
