package com.example.Notificaciones.model;

import jakarta.persistence.*;

@Entity
@Table(name="notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;

    private Long ordenId;

    private String mensaje;

    private String tipo;

    private String estado;


    public Notificacion() {
    }


    public Notificacion(Long id, String usuarioId, Long ordenId,
                        String mensaje, String tipo, String estado) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.ordenId = ordenId;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.estado = estado;
    }


    public Long getId() {
        return id;
    }


    public String getUsuarioId() {
        return usuarioId;
    }


    public Long getOrdenId() {
        return ordenId;
    }


    public String getMensaje() {
        return mensaje;
    }


    public String getTipo() {
        return tipo;
    }


    public String getEstado() {
        return estado;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }


    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }


    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }
}
