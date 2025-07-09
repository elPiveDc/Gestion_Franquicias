package com.MiNegocio.accesousuarios.model;

import com.MiNegocio.configuracioncentral.domain.TipoBD;

public class UsuarioFranquicia {
    private String nombreUsuario;
    private String nombreBD;
    private TipoBD tipoBD;
    private boolean esAdmin;

    public UsuarioFranquicia(String nombreUsuario, String nombreBD, TipoBD tipoBD, boolean esAdmin) {
        this.nombreUsuario = nombreUsuario;
        this.nombreBD = nombreBD;
        this.tipoBD = tipoBD;
        this.esAdmin = esAdmin;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public TipoBD getTipoBD() {
        return tipoBD;
    }

    public boolean isAdmin() {
        return esAdmin;
    }
}
