package com.MiNegocio.configuracioncentral.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Franquicia {
    private int id;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private EstadoFranquicia estado;
    private Usuario propietario;
    private List<BaseDatosFranquicia> basesDatos;

    public Franquicia() {
        this.basesDatos = new ArrayList<>();
    }

    public Franquicia(int id, String nombre, LocalDateTime fechaCreacion, EstadoFranquicia estado, Usuario propietario) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.propietario = propietario;
        this.basesDatos = new ArrayList<>();
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public EstadoFranquicia getEstado() { return estado; }
    public void setEstado(EstadoFranquicia estado) { this.estado = estado; }

    public Usuario getPropietario() { return propietario; }
    public void setPropietario(Usuario propietario) { this.propietario = propietario; }

    public List<BaseDatosFranquicia> getBasesDatos() { return basesDatos; }
    public void setBasesDatos(List<BaseDatosFranquicia> basesDatos) { this.basesDatos = basesDatos; }
}
