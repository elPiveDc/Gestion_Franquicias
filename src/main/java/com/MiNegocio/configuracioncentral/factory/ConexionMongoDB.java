package com.MiNegocio.configuracioncentral.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConexionMongoDB {

    private static MongoClient mongoClient;

    public static MongoClient getMongoConexion() {
        if (mongoClient == null) {
            String uri = cargarURIDesdeConfig();
            mongoClient = MongoClients.create(uri);
        }
        return mongoClient;
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    private static String cargarURIDesdeConfig() {
        Properties props = new Properties();

        try (InputStream input = ConexionMongoDB.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró config.properties");
            }

            props.load(input);
            String uri = props.getProperty("mongodb.uri");

            if (uri == null || uri.trim().isEmpty()) {
                throw new RuntimeException("La propiedad mongodb.uri no está definida");
            }

            return uri;

        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración MongoDB", e);
        }
    }
}
