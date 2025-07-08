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
        String createUserSQL = "CREATE USER " + usuario + " WITH ENCRYPTED PASSWORD '" + password + "'";
        String grantSQL = "GRANT ALL PRIVILEGES ON DATABASE " + nombreBD + " TO " + usuario;

        try (Connection conn = ConexionMultiBDFactory.getConexion("postgresql");
             Statement stmt = conn.createStatement()) {

            // Crear la base de datos
            stmt.executeUpdate(createDatabaseSQL);

            // Crear el usuario
            stmt.executeUpdate(createUserSQL);

            // Otorgar permisos
            stmt.executeUpdate(grantSQL);

        } catch (SQLException e) {
            throw new RuntimeException("Error creando base de datos PostgreSQL: " + e.getMessage(), e);
        }
    }
}
