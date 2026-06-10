package com.example.Pagos.model;
import jakarta.persistence.*;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private Long ordenId;
    
    private String metodoPago;

    private float monto;

    private String estado;

    public Pago(){
    }    
    
    public Long getId(){
            return id;
        }
    public Long getOrdenId() {
        return ordenId;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public float getMonto() {
        return monto;
    }
    public String getEstado() {
        return estado;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public void setMonto(float monto) {
        this.monto = monto;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
