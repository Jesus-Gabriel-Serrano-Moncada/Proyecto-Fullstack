package com.example.Envios.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private Long ordenId;

    private String direccion;

    private String empresaTransporte;

    private String numeroSeguimiento;

    private String estado;

    private LocalDate fechaEnvio;

    private LocalDate fechaEntrega;

    public Envio() {
    }

    public Envio(Long id, Long ordenId, String direccion,
            String empresaTransporte, String numeroSeguimiento,
            String estado, LocalDate fechaEnvio,
            LocalDate fechaEntrega) {

        this.id = id;
        this.ordenId = ordenId;
        this.direccion = direccion;
        this.empresaTransporte = empresaTransporte;
        this.numeroSeguimiento = numeroSeguimiento;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
    }


    public Long getId() {
        return id;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmpresaTransporte() {
        return empresaTransporte;
    }

    public String getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmpresaTransporte(String empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }

    public void setNumeroSeguimiento(String numeroSeguimiento) {
        this.numeroSeguimiento = numeroSeguimiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}