package com.MiNegocio.configuracioncentral.factory;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Properties;

public class ConexionMultiBDFactory {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConexionMultiBDFactory.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se pudo encontrar config.properties");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando config.properties", e);
        }
    }

    public static Connection getConexion(String tipoBD) throws Exception {
        String prefix = tipoBD.toLowerCase(); // mysql, oracle, etc.

        String url = props.getProperty(prefix + ".url");
        String user = props.getProperty(prefix + ".user");
        String pass = props.getProperty(prefix + ".password");

        if (url == null || user == null) {
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
                throw new IllegalArgumentException("Tipo de BD no soportado: " + prefix);
        }

        return DriverManager.getConnection(url, user, pass);
    }

    public static MongoClient getMongoConexion() {
        String url = props.getProperty("mongodb.url");
        String user = props.getProperty("mongodb.user");
        String pass = props.getProperty("mongodb.password");

        MongoCredential credential = MongoCredential.createCredential(user, "admin", pass.toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToClusterSettings(builder
                        -> builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017))))
                .build();

        return MongoClients.create(settings);
    }

}
