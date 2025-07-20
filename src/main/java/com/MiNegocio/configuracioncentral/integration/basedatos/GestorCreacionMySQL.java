package com.MiNegocio.configuracioncentral.integration.basedatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class GestorCreacionMySQL implements GestorCreacionBD {

    @Override
    public void crearBaseDatos(BaseDatosFranquicia bd) throws Exception {
        String nombreBD = bd.getNombreBD();
        String usuario = bd.getUsuarioBD();
        String password = bd.getPasswordHash();

        try (Connection conn = ConexionBDFactory.getConexion();
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + nombreBD + "`");
            stmt.executeUpdate("CREATE USER IF NOT EXISTS '" + usuario + "'@'localhost' IDENTIFIED BY '" + password + "'");
            stmt.executeUpdate("GRANT ALL PRIVILEGES ON `" + nombreBD + "`.* TO '" + usuario + "'@'localhost'");
            stmt.executeUpdate("FLUSH PRIVILEGES");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,
                    "Error creando la base de datos y usuario:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Error creando la base de datos y usuario: " + e.getMessage(), e);
        }
    }
}
