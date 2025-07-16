package com.MiNegocio.configuracioncentral.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.datastax.astra.client.DataAPIClient;
import com.datastax.astra.client.databases.Database;

public class ConexionNoSQL {

    private static MongoClient mongoClient;

    public static MongoClient getMongoConexion() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(
                    "mongodb+srv://User:f5tcS8dl1aQcbv7x@practicacluster.gmgob8m.mongodb.net/?retryWrites=true&w=majority"
            );
        }

        return mongoClient;
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    private static final String ASTRA_TOKEN = "AstraCS:khLXKvAqTzDIQmIKtrKmexSK:1f24a37453e5cb2a285e38d8946f3fbe86218215ad0b8f8e15272a6836c88d7d";
    private static final String ASTRA_DB_URL = "https://139e51de-a2a1-41c6-abfc-70dc7fd52a2d-us-east-2.apps.astra.datastax.com";

    private static final DataAPIClient client = new DataAPIClient(ASTRA_TOKEN);
    private static final Database db = client.getDatabase(ASTRA_DB_URL);

    public static Database getDatabase() {
        return db;
    }
}
