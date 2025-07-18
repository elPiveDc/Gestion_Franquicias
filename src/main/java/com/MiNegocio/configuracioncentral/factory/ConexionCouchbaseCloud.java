package com.MiNegocio.configuracioncentral.factory;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Collection;
import com.couchbase.client.core.env.SecurityConfig;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.ClusterOptions;

public class ConexionCouchbaseCloud {

    private static final String CONNECTION_STRING = "couchbases://cb.36cn6vqkwreh9rrc.cloud.couchbase.com";
    private static final String USERNAME = "Servicio_Franquicias";
    private static final String PASSWORD = "iaq!pQZ!2p5dCNh";
    private static final String BUCKET_NAME = "miBucket";

    private Cluster cluster;
    private Bucket bucket;
    private Collection collection;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public ConexionCouchbaseCloud() {
        ClusterEnvironment env = ClusterEnvironment.builder()
            .securityConfig(SecurityConfig.enableTls(true))
            .build();

        cluster = Cluster.connect(
            CONNECTION_STRING,
            ClusterOptions.clusterOptions(USERNAME, PASSWORD).environment(env)
        );

        bucket = cluster.bucket(BUCKET_NAME);
        bucket.waitUntilReady(java.time.Duration.ofSeconds(10));
        collection = bucket.defaultCollection();
    }

    public void subirDocumento(String id, String json) {
        collection.upsert(id, com.couchbase.client.java.json.JsonObject.fromJson(json));
    }

    public void cerrar() {
        cluster.disconnect();
    }

    public static void main(String[] args) {
        ConexionCouchbaseCloud conexion = new ConexionCouchbaseCloud();
        conexion.subirDocumento("doc1", "{\"nombre\":\"Juan\", \"edad\":30}");
        System.out.println("Documento subido!");
        conexion.cerrar();
    }
}


