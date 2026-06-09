package com.example.UsuarioMongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nombre;
    private String correo;
    private int edad;
    private String rol;
    private String contraseña;
    
    public Usuario() {}

    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public int getEdad() {
        return edad;
    }
    public String getRol() {
        return rol;
    }
    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}

