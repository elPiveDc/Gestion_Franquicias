package com.MiNegocio.configuracioncentral.factory;

import com.couchbase.client.java.*;
import com.couchbase.client.java.json.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConexionCouch {

    private Cluster cluster;
    private Bucket bucket;
    private Collection collection;

    private String connectionString;
    private String bucketName;
    private String username;
    private String password;

    public ConexionCouch() {
        cargarConfiguracion();

        // Conectar sin TLS (local)
        cluster = Cluster.connect(connectionString, username, password);

        bucket = cluster.bucket(bucketName);
        bucket.waitUntilReady(java.time.Duration.ofSeconds(10));
        collection = bucket.defaultCollection();
    }

    private void cargarConfiguracion() {
        Properties props = new Properties();

        try (InputStream input = ConexionBDFactory.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo config.properties");
            }

            props.load(input);

            connectionString = props.getProperty("couchbase.host");
            bucketName = props.getProperty("couchbase.bucket");
            username = props.getProperty("couchbase.username");
            password = props.getProperty("couchbase.password");

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo configuración de Couchbase", e);
        }
    }

    public void subirDocumento(String id, String json) {
        collection.upsert(id, JsonObject.fromJson(json));
    }

    public void cerrar() {
        cluster.disconnect();
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
