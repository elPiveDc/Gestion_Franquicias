package com.MiNegocio.configuracioncentral.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConexionMongoDB {

    private static MongoClient mongoClient;

    public static MongoClient getMongoConexion() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(
                    "mongodb+srv://User:f5tcS8dl1aQcbv7x@practicacluster.gmgob8m.mongodb.net/"
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

}
