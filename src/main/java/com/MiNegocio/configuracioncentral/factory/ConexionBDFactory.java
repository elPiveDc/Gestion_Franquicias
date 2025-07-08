package com.MiNegocio.configuracioncentral.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBDFactory {

    private static Connection conexion;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = ConexionBDFactory.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("No se pudo encontrar el archivo config.properties");
            }

            Properties prop = new Properties();
            prop.load(input);

            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");

            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                        System.out.println("Conexión cerrada correctamente al apagar el sistema.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al cargar configuración de la BD", e);
        }
    }

    private ConexionBDFactory() {
        // Evitar instanciación
    }

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }
}
