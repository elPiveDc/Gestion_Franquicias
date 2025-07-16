package com.MiNegocio.configuracioncentral.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String passwordHash;
    private LocalDateTime fechaRegistro;
    private List<Franquicia> franquicias;

    public Usuario() {
        this.franquicias = new ArrayList<>();
    }

    public Usuario(String nombre, String correo, String passwordHash, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.fechaRegistro = fechaRegistro;
        this.franquicias = new ArrayList<>();
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Franquicia> getFranquicias() {
        return franquicias;
    }

    public void setFranquicias(List<Franquicia> franquicias) {
        this.franquicias = franquicias;
    }
}
