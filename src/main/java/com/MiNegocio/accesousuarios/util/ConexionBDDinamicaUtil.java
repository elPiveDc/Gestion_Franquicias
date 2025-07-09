package com.MiNegocio.accesousuarios.util;

import com.MiNegocio.accesousuarios.model.ConexionFranquicia;
import com.MiNegocio.configuracioncentral.domain.TipoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDDinamicaUtil {

    public static Connection conectar(ConexionFranquicia datos) throws SQLException {
        TipoBD tipo = datos.getTipoBD();
        String url = datos.getUrlConexion();
        String usuario = datos.getUsuarioConexion();
        String password = datos.getPasswordConexion();

        try {
            switch (tipo) {
                case MYSQL:
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    break;
                case POSTGRESQL:
                    Class.forName("org.postgresql.Driver");
                    break;
                case ORACLE:
                    Class.forName("oracle.jdbc.OracleDriver");
                    break;
                default:
                    throw new UnsupportedOperationException("Tipo de base de datos no soportado: " + tipo);
            }

            return DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver para " + tipo, e);
        }
    }
}