package com.MiNegocio.accesousuarios.util;

import com.MiNegocio.accesousuarios.model.ConexionFranquicia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionFranquiciaFactory {

    private ConexionFranquiciaFactory() {
    }

    public static Connection crearConexion(ConexionFranquicia info) throws SQLException {
        if (info == null) {
            throw new IllegalArgumentException("Información de conexión nula");
        }

        return DriverManager.getConnection(
            info.getUrlConexion(),
            info.getUsuarioConexion(),
            info.getPasswordConexion()
        );
    }
}