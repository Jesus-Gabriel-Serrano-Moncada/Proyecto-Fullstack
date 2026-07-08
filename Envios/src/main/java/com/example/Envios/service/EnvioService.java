package com.example.Envios.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Envios.client.OrdenClient;
import com.example.Envios.model.Envio;
import com.example.Envios.repository.EnvioRepository;

@Service
public class EnvioService {

    private final EnvioRepository repo;
    private final OrdenClient ordenClient;

    public EnvioService(
            EnvioRepository repo,
            OrdenClient ordenClient) {

        this.repo = repo;
        this.ordenClient = ordenClient;
    }


    public List<Envio> listar() {
        return repo.findAll();
    }


    public Envio buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }


    public List<Envio> buscarPorOrden(Long ordenId) {
        return repo.findByOrdenId(ordenId);
    }


    public List<Envio> buscarPorEstado(String estado) {
        return repo.findByEstado(estado);
    }


    public ResponseEntity<?> crearEnvio(Envio envio) {

        String validacion = validarEnvio(envio);

        if (validacion != null) {
            return ResponseEntity.badRequest()
                    .body(validacion);
        }


        try {

            String orden = ordenClient.buscarOrden(envio.getOrdenId());

            if (orden == null) {
                return ResponseEntity.badRequest()
                        .body("La orden no existe");
            }


        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Error verificando orden: " + e.getMessage());
        }


        if (envio.getEstado() == null ||
            envio.getEstado().trim().isEmpty()) {

            envio.setEstado("PREPARANDO");
        }


        if (envio.getFechaEnvio() == null) {
            envio.setFechaEnvio(LocalDate.now());
        }


        return ResponseEntity.ok(repo.save(envio));
    }


    public ResponseEntity<?> actualizarEnvio(
            Long id,
            Envio envio) {


        if (!repo.existsById(id)) {

            return ResponseEntity.status(404)
                    .body("El envío con id < " + id + " > no existe");
        }


        String validacion = validarEnvio(envio);

        if (validacion != null) {

            return ResponseEntity.badRequest()
                    .body(validacion);
        }


        envio.setId(id);

        return ResponseEntity.ok(repo.save(envio));
    }



    public ResponseEntity<?> eliminarEnvio(Long id) {


        if (!repo.existsById(id)) {

            return ResponseEntity.status(404)
                    .body("El envío con id < " + id + " > no existe");
        }


        repo.deleteById(id);

        return ResponseEntity.ok(
                "Envío eliminado correctamente");
    }



    private String validarEnvio(Envio envio) {


        if (envio == null)
            return "El envío no puede ser nulo";


        if (envio.getOrdenId() == null)
            return "Debe indicar la orden asociada";


        if (envio.getDireccion() == null ||
            envio.getDireccion().trim().isEmpty())

            return "La dirección no puede estar vacía";


        if (envio.getEmpresaTransporte() == null ||
            envio.getEmpresaTransporte().trim().isEmpty())

            return "Debe indicar la empresa de transporte";


        return null;
    }

}