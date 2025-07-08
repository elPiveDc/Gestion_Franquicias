package com.MiNegocio.configuracioncentral.integration.basedatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorCreacionOracle implements GestorCreacionBD {

    @Override
    public void crearBaseDatos(BaseDatosFranquicia bd) throws Exception {
        String usuario = bd.getUsuarioBD();
        String password = bd.getPasswordHash();

        String createUserSQL = "CREATE USER " + usuario + " IDENTIFIED BY " + password;
        String grantSQL = "GRANT CONNECT, RESOURCE TO " + usuario;

        try (Connection conn = ConexionMultiBDFactory.getConexion("oracle"); 
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createUserSQL);
            stmt.executeUpdate(grantSQL);

        } catch (SQLException e) {
            throw new RuntimeException("Error creando usuario Oracle: " + e.getMessage(), e);
        }
    }
}