package com.example.Carrito.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Carrito.client.ProductoClient;
import com.example.Carrito.client.UsuarioClient;
import com.example.Carrito.model.Carrito;
import com.example.Carrito.repository.CarritoRepository;

@Service
public class CarritoService {

    private final CarritoRepository repo;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;


    public CarritoService(
            CarritoRepository repo,
            UsuarioClient usuarioClient,
            ProductoClient productoClient) {

        this.repo = repo;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }


    public ResponseEntity<?> obtenerTodos() {

        List<Carrito> lista = repo.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No hay productos en el carrito");
        }

        return ResponseEntity.ok(lista);
    }


    public ResponseEntity<?> obtenerPorId(Long id) {

        Carrito c = repo.findById(id).orElse(null);

        if (c == null) {
            return ResponseEntity.status(404)
                    .body("No existe carrito con id < " + id + " >");
        }

        return ResponseEntity.ok(c);
    }


    public ResponseEntity<?> guardar(Carrito c) {


        if (c.getUsuarioId() == null ||
            c.getProductoId() == null ||
            c.getCantidad() <= 0) {

            return ResponseEntity.badRequest()
                    .body("Datos inválidos en el carrito");
        }


        try {

            String usuario =
                    usuarioClient.buscarUsuario(c.getUsuarioId());


            if (usuario == null || usuario.isBlank()) {

                return ResponseEntity.badRequest()
                        .body("El usuario no existe");
            }


        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Error consultando usuario: " + e.getMessage());
        }



        try {

            String producto =
                    productoClient.buscarProducto(c.getProductoId());


            if (producto == null || producto.isBlank()) {

                return ResponseEntity.badRequest()
                        .body("El producto no existe");
            }


        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Error consultando producto: " + e.getMessage());
        }


        return ResponseEntity.ok(repo.save(c));
    }



    public ResponseEntity<?> eliminar(Long id) {

        Carrito c = repo.findById(id).orElse(null);

        if (c == null) {
            return ResponseEntity.status(404)
                    .body("No existe carrito con id < " + id + " >");
        }


        repo.deleteById(id);

        return ResponseEntity.ok(
                "Carrito eliminado correctamente");
    }



    public ResponseEntity<?> actualizar(Long id, Carrito c) {


        Carrito existente = repo.findById(id)
                .orElse(null);


        if (existente == null) {

            return ResponseEntity.status(404)
                    .body("No existe carrito con id < " + id + " >");
        }


        if (c.getCantidad() < 1) {

            return ResponseEntity.badRequest()
                    .body("La cantidad debe ser mayor a 0");
        }


        c.setId(id);


        return ResponseEntity.ok(repo.save(c));
    }

}