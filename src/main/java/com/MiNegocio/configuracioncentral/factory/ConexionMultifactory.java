package com.MiNegocio.configuracioncentral.factory;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConexionMultifactory {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConexionMultifactory.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                JOptionPane.showMessageDialog(null,
                    "No se pudo encontrar config.properties:\n",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("No se pudo encontrar config.properties");
            }
            props.load(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error cargando config.properties:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Error cargando config.properties", e);
        }
    }

    public static Connection getConexion(String tipoBD) throws Exception {

        String prefix = tipoBD.toLowerCase(); // mysql, oracle, etc.

        String url = props.getProperty(prefix + ".url");
        String user = props.getProperty(prefix + ".user");
        String pass = props.getProperty(prefix + ".password");

        if (url == null || user == null) {
            
            JOptionPane.showMessageDialog(null,
                    "No hay configuración para el tipo de BD: " + tipoBD,
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            throw new IllegalArgumentException("No hay configuración para el tipo de BD: " + tipoBD);
        }

        switch (prefix) {
            case "mysql":
                Class.forName("com.mysql.cj.jdbc.Driver");
                break;
            case "oracle":
                Class.forName("oracle.jdbc.OracleDriver");
                break;
            case "postgresql":
                Class.forName("org.postgresql.Driver");
                break;

            default:
                JOptionPane.showMessageDialog(null,
                    "Tipo de BD no soportado: " + prefix,
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
                throw new IllegalArgumentException("Tipo de BD no soportado: " + prefix);
        }

        return DriverManager.getConnection(url, user, pass);
    }

    public static Connection getConexion(String tipoBD, String url) throws Exception {
        String prefix = tipoBD.toLowerCase();

        String user = props.getProperty(prefix + ".user");
        String pass = props.getProperty(prefix + ".password");

        if (url == null || user == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay configuración para el tipo de BD: " + tipoBD,
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            throw new IllegalArgumentException("No hay configuración para el tipo de BD: " + tipoBD);
        }

        switch (prefix) {
            case "mysql":
                Class.forName("com.mysql.cj.jdbc.Driver");
                break;
            case "oracle":
                Class.forName("oracle.jdbc.OracleDriver");
                break;
            case "postgresql":
                Class.forName("org.postgresql.Driver");
                break;

            default:
                JOptionPane.showMessageDialog(null,
                    "Tipo de BD no soportado: " + prefix,
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
                throw new IllegalArgumentException("Tipo de BD no soportado: " + prefix);
        }

        return DriverManager.getConnection(url, user, pass);
    }

    public static Connection getConexion(BaseDatosFranquicia bd) throws Exception {

        String tipo = bd.getTipo().toString().toLowerCase(); // "mysql", "oracle", etc.
        String url = bd.getUrlConexion();
        String user = bd.getUsuarioBD();
        String pass = bd.getPasswordHash();

        switch (tipo) {
            case "mysql":
                Class.forName("com.mysql.cj.jdbc.Driver");
                break;
            case "oracle":
                Class.forName("oracle.jdbc.OracleDriver");
                break;
            case "postgresql":
                Class.forName("org.postgresql.Driver");
                break;
            default:
                JOptionPane.showMessageDialog(null,
                    "Tipo de BD no soportado: " + tipo,
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
                throw new IllegalArgumentException("Tipo de BD no soportado: " + tipo);
        }

        return DriverManager.getConnection(url, user, pass);
    }

}
