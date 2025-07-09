package com.MiNegocio.accesousuarios.model;

import com.MiNegocio.configuracioncentral.domain.TipoBD;

public class ConexionFranquicia {

    private int idBD;
    private String nombreBD;
    private TipoBD tipoBD;
    private String urlConexion;
    private String usuarioConexion;
    private String passwordConexion;

    public ConexionFranquicia(int idBD, String nombreBD, TipoBD tipoBD,
            String urlConexion, String usuarioConexion, String passwordConexion) {
        this.idBD = idBD;
        this.nombreBD = nombreBD;
        this.tipoBD = tipoBD;
        this.urlConexion = urlConexion;
        this.usuarioConexion = usuarioConexion;
        this.passwordConexion = passwordConexion;
    }

    public ConexionFranquicia() {
    }

    // Getters
    public int getIdBD() {
        return idBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public TipoBD getTipoBD() {
        return tipoBD;
    }

    public String getUrlConexion() {
        return urlConexion;
    }

    public String getUsuarioConexion() {
        return usuarioConexion;
    }

    public String getPasswordConexion() {
        return passwordConexion;
    }

    // Setters
    public void setIdBD(int idBD) {
        this.idBD = idBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public void setTipoBD(TipoBD tipoBD) {
        this.tipoBD = tipoBD;
    }

    public void setUrlConexion(String urlConexion) {
        this.urlConexion = urlConexion;
    }

    public void setUsuarioConexion(String usuarioConexion) {
        this.usuarioConexion = usuarioConexion;
    }

    public void setPasswordConexion(String passwordConexion) {
        this.passwordConexion = passwordConexion;
    }

    @Override
    public String toString() {
        return "ConexionFranquicia{"
                + "idBD=" + idBD
                + ", nombreBD='" + nombreBD + '\''
                + ", tipoBD=" + tipoBD
                + ", urlConexion='" + urlConexion + '\''
                + ", usuarioConexion='" + usuarioConexion + '\''
                + '}';
    }
}
