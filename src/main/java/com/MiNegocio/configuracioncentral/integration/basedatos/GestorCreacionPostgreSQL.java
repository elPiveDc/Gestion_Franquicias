package com.MiNegocio.configuracioncentral.integration.basedatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorCreacionPostgreSQL implements GestorCreacionBD {

    @Override
    public void crearBaseDatos(BaseDatosFranquicia bd) throws Exception {
        String nombreBD = bd.getNombreBD();
        String usuario = bd.getUsuarioBD();
        String password = bd.getPasswordHash();

        String createDatabaseSQL = "CREATE DATABASE " + nombreBD;
        String createUserSQL = "CREATE USER " + usuario + " WITH PASSWORD '" + password + "'";
        String grantSQL = "GRANT ALL PRIVILEGES ON DATABASE " + nombreBD + " TO " + usuario;

        // 1. Conexión inicial como superusuario para crear la BD y el usuario
        try (Connection conn = ConexionMultiBDFactory.getConexion("postgresql"); 
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createDatabaseSQL);
            stmt.executeUpdate(createUserSQL);
            stmt.executeUpdate(grantSQL);

            System.out.println("Base de datos y usuario creados correctamente en PostgreSQL.");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
