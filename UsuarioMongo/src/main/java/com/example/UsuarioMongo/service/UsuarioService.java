package com.example.UsuarioMongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.UsuarioMongo.model.Usuario;
import com.example.UsuarioMongo.repository.UsuarioRepository;

@Service
public class UsuarioService {
        
    @Autowired
    private UsuarioRepository repo;

    /* GET MAPPING */
    /* listar todos los usuarios */
    public List<Usuario> listar() {
        return repo.findAll();
    }

    /* Buscar a un usuario por su ID */
    public ResponseEntity<?> buscarPorId(String id) {
        Usuario existe = repo.findById(id).orElse(null);

        if (existe == null) {
            return ResponseEntity.status(404)
                    .body("No existe un usuario con la id (" + id + ")");
        }

        return ResponseEntity.ok(existe);
    }

    /* POST MAPPING */
    /* Crear un usuario */
    public ResponseEntity<?> crear(Usuario u) {

        String[] roles = new String[2];
        roles[0] = "comprador";
        roles[1] = "vendedor";

        /* Validacion de nombre */
        if (u.getNombre() == null || u.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("ERROR!\nEl nombre del usuario no puede estar vacio!");
        }

        if (u.getNombre().trim().length() < 3) {
            return ResponseEntity.badRequest()
                    .body("ERROR!\nEl nombre del usuario no puede tener menos de 3 caracteres!\nNombre actual: (" + u.getNombre() + ")");
        }

        /* Validaciones de correo */
        if (u.getCorreo() == null || u.getCorreo().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("El correo no puede estar vacio!");
        }

        if (!u.getCorreo().toLowerCase().contains("@gmail.com")
                && !u.getCorreo().toLowerCase().contains("@hotmail.com")
                && !u.getCorreo().toLowerCase().contains("@outlook.com")
                && !u.getCorreo().toLowerCase().contains("@yahoo.com")) {

            return ResponseEntity.badRequest()
                    .body("El correo debe pertenecer a un dominio permitido (gmail, hotmail, outlook o yahoo)\nCorreo actual: (" + u.getCorreo() + ")");
        }

        /* Validaciones de edad */
        if (u.getEdad() <= 18) {
            return ResponseEntity.badRequest()
                    .body("La edad debe ser mayor a 18!\nEdad actual: (" + u.getEdad() + ")");
        }

        /* Validaciones de Rol */
        if (u.getRol() == null || u.getRol().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("ERROR!\nEl rol del usuario no puede estar vacio!");
        }

        if (!u.getRol().equalsIgnoreCase(roles[0]) &&
            !u.getRol().equalsIgnoreCase(roles[1])) {

            return ResponseEntity.badRequest()
                    .body("El rol debe ser [Vendedor] o [Comprador]\nRol actual: (" + u.getRol() + ")");
        }

        /* Si pasa todas las validaciones */
        return ResponseEntity.ok(repo.save(u));
    }

    /* PUT MAPPING */
    /* Actualizar a un usuario por ID */
    public ResponseEntity<?> actualizar(String id, Usuario u) {

        String[] roles = new String[2];
        roles[0] = "comprador";
        roles[1] = "vendedor";

        /* Existe */
        Usuario existe = repo.findById(id).orElse(null);

        if (existe == null) {
            return ResponseEntity.status(404)
                    .body("No existe un usuario con la id (" + id + ")");
        }

        /* Validacion de nombre */
        if (u.getNombre() == null || u.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("ERROR!\nEl nombre del usuario no puede estar vacio!");
        }

        if (u.getNombre().trim().length() < 3) {
            return ResponseEntity.badRequest()
                    .body("ERROR!\nEl nombre del usuario no puede tener menos de 3 caracteres!\nNombre actual: (" + u.getNombre() + ")");
        }

        /* Validaciones de correo */
        if (u.getCorreo() == null || u.getCorreo().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("El correo no puede estar vacio!");
        }

        if (!u.getCorreo().toLowerCase().contains("@gmail.com")
                && !u.getCorreo().toLowerCase().contains("@hotmail.com")
                && !u.getCorreo().toLowerCase().contains("@outlook.com")
                && !u.getCorreo().toLowerCase().contains("@yahoo.com")) {

            return ResponseEntity.badRequest()
                    .body("El correo debe pertenecer a un dominio permitido (gmail, hotmail, outlook o yahoo)\nCorreo actual: (" + u.getCorreo() + ")");
        }

        /* Validaciones de edad */
        if (u.getEdad() <= 18) {
            return ResponseEntity.badRequest()
                    .body("El usuario debe ser mayor de edad!\nEdad actual: (" + u.getEdad() + ")");
        }

        /* Validaciones de Rol */
        if (u.getRol() == null || u.getRol().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("ERROR!\nEl rol del usuario no puede estar vacio!");
        }

        if (!u.getRol().equalsIgnoreCase(roles[0]) &&
            !u.getRol().equalsIgnoreCase(roles[1])) {

            return ResponseEntity.badRequest()
                    .body("El rol debe ser [Vendedor] o [Comprador]\nRol actual: (" + u.getRol() + ")");
        }

        /* Si pasa todas las validaciones */
        u.setId(id);

        return ResponseEntity.ok(repo.save(u));
    }

    /* DELETE MAPPING */
    /* Borrar a un usuario por su ID */
    public ResponseEntity<?> borrarPorId(String id) {

        Usuario existe = repo.findById(id).orElse(null);

        if (existe == null) {
            return ResponseEntity.status(404)
                    .body("No existe un usuario con la id (" + id + ")");
        }

        repo.deleteById(id);

        return ResponseEntity.ok("Usuario Id: " + id + " ha sido eliminado!");
    }
}