package com.MiNegocio.accesousuarios.control;

import com.MiNegocio.accesousuarios.model.UsuarioFranquicia;
import com.MiNegocio.configuracioncentral.domain.TipoBD;

public class SesionUsuarioFranquiciaController {

    private static SesionUsuarioFranquiciaController instancia;

    private UsuarioFranquicia conexionFranquicia;

    private SesionUsuarioFranquiciaController() {
        // Constructor privado para Singleton
    }

    public static SesionUsuarioFranquiciaController getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuarioFranquiciaController();
        }
        return instancia;
    }

    public void iniciarSesion(UsuarioFranquicia conexion) {

        this.conexionFranquicia = conexion;
    }

    public void cerrarSesion() {

        this.conexionFranquicia = null;
    }


    public UsuarioFranquicia getConexionFranquicia() {
        return conexionFranquicia;
    }

    public TipoBD getTipoBD() {
        return (conexionFranquicia != null) ? conexionFranquicia.getTipoBD() : null;
    }

    public boolean haySesionActiva() {
        return  conexionFranquicia != null;
    }

    @Override
    public String toString() {
        return "SesionUsuarioFranquiciaController{" +
                ", conexionFranquicia=" + conexionFranquicia +
                '}';
    }
}