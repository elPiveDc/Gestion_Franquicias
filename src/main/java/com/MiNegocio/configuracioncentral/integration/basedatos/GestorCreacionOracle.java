package com.MiNegocio.configuracioncentral.integration.basedatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class GestorCreacionOracle implements GestorCreacionBD {

    @Override
    public void crearBaseDatos(BaseDatosFranquicia bd) throws Exception {
        String usuario = bd.getUsuarioBD();
        String password = bd.getPasswordHash();

        // Crear el usuario
        String createUserSQL = "CREATE USER " + usuario + " IDENTIFIED BY " + password;

        // Privilegios básicos
        String grantBasicSQL = "GRANT CONNECT, RESOURCE TO " + usuario;

        // Privilegios adicionales útiles para desarrollo
        String[] privilegiosExtras = {
            "GRANT CREATE SESSION TO " + usuario,
            "GRANT CREATE TABLE TO " + usuario,
            "GRANT CREATE VIEW TO " + usuario,
            "GRANT CREATE PROCEDURE TO " + usuario,
            "GRANT CREATE SEQUENCE TO " + usuario,
            "GRANT CREATE TRIGGER TO " + usuario,
            "GRANT CREATE TYPE TO " + usuario,
            "GRANT CREATE SYNONYM TO " + usuario,
            "GRANT UNLIMITED TABLESPACE TO " + usuario
        };

        try (Connection conn = ConexionMultiBDFactory.getConexion("oracle"); Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createUserSQL);
            stmt.executeUpdate(grantBasicSQL);

            for (String grant : privilegiosExtras) {
                stmt.executeUpdate(grant);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error creando la base de datos y usuario:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Error creando usuario Oracle: " + e.getMessage(), e);
        }
    }

}
