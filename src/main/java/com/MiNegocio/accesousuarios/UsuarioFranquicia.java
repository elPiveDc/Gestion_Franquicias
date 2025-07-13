package com.MiNegocio.accesousuarios;

public class UsuarioFranquicia {
    private int idUsuario;
    private String nombreUsuario;
    private boolean esAdmin;

    public UsuarioFranquicia(int idUsuario, String nombreUsuario, boolean esAdmin) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.esAdmin = esAdmin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }
}

