package com.MiNegocio.configuracioncentral.domain;

import java.util.Date;

public class AuditoriaCarga {
    private String usuario;
    private String baseDatos;
    private String nombreTabla;
    private String metodoCarga;
    private Date fecha;

    public AuditoriaCarga(String usuario, String baseDatos, String nombreTabla, String metodoCarga) {
        this.usuario = usuario;
        this.baseDatos = baseDatos;
        this.nombreTabla = nombreTabla;
        this.metodoCarga = metodoCarga;
        this.fecha = new Date();
    }

    // Getters y setters (puedes generarlos con tu IDE)

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getMetodoCarga() {
        return metodoCarga;
    }

    public void setMetodoCarga(String metodoCarga) {
        this.metodoCarga = metodoCarga;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
