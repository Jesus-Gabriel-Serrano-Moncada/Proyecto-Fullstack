package com.example.Notificaciones.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Notificaciones.client.OrdenClient;
import com.example.Notificaciones.client.UsuarioClient;
import com.example.Notificaciones.model.Notificacion;
import com.example.Notificaciones.repository.NotificacionRepository;


@Service
public class NotificacionService {


    private final NotificacionRepository repo;
    private final UsuarioClient usuarioClient;
    private final OrdenClient ordenClient;


    public NotificacionService(
            NotificacionRepository repo,
            UsuarioClient usuarioClient,
            OrdenClient ordenClient) {

        this.repo = repo;
        this.usuarioClient = usuarioClient;
        this.ordenClient = ordenClient;
    }



    public ResponseEntity<?> listar() {

        List<Notificacion> lista = repo.findAll();

        if (lista.isEmpty()) {

            return ResponseEntity.status(404)
                    .body("No existen notificaciones registradas");
        }

        return ResponseEntity.ok(lista);
    }



    public List<Notificacion> buscarPorUsuario(String usuarioId) {

        return repo.findByUsuarioId(usuarioId);
    }



    public List<Notificacion> buscarPorOrden(Long ordenId) {

        return repo.findByOrdenId(ordenId);
    }




    public ResponseEntity<?> crearNotificacion(Notificacion n) {


        try {

            String usuario = usuarioClient.buscarUsuario(n.getUsuarioId());

            if (usuario == null || usuario.isBlank()) {

                return ResponseEntity.badRequest()
                        .body("El usuario no existe");
            }


        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("ERROR FEIGN USUARIO: " + e.getMessage());
        }



        try {

            String orden = ordenClient.buscarOrden(n.getOrdenId());

            if (orden == null || orden.isBlank()) {

                return ResponseEntity.badRequest()
                        .body("La orden no existe");
            }


        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("ERROR FEIGN ORDEN: " + e.getMessage());
        }



        String validacion = validarNotificacion(n);


        if (validacion != null) {

            return ResponseEntity.badRequest()
                    .body(validacion);
        }



        if (n.getEstado() == null || n.getEstado().trim().isEmpty()) {

            n.setEstado("PENDIENTE");
        }



        return ResponseEntity.ok(repo.save(n));
    }





    public ResponseEntity<?> actualizarNotificacion(
            Long id,
            Notificacion n) {


        Notificacion existente = repo.findById(id)
                .orElse(null);


        if (existente == null) {

            return ResponseEntity.status(404)
                    .body("La notificación con id < " + id + " > no existe");
        }



        String validacion = validarNotificacion(n);


        if (validacion != null) {

            return ResponseEntity.badRequest()
                    .body(validacion);
        }



        n.setId(id);


        return ResponseEntity.ok(repo.save(n));
    }





    public ResponseEntity<?> eliminarNotificacion(Long id) {


        if (!repo.existsById(id)) {

            return ResponseEntity.status(404)
                    .body("La notificación con id < " + id + " > no existe");
        }


        repo.deleteById(id);


        return ResponseEntity.ok(
                "Notificación eliminada correctamente");
    }





    private String validarNotificacion(Notificacion n) {


        if (n == null)

            return "La notificación no puede ser nula";



        if (n.getUsuarioId() == null ||
            n.getUsuarioId().trim().isEmpty())

            return "Debe indicar el usuario";



        if (n.getOrdenId() == null)

            return "Debe indicar la orden";



        if (n.getMensaje() == null ||
            n.getMensaje().trim().isEmpty())

            return "El mensaje no puede estar vacío";



        if (n.getTipo() == null ||
            n.getTipo().trim().isEmpty())

            return "Debe indicar el tipo de notificación";



        return null;
    }

}