package conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import config.DBConfig;

public class MongoConnector {

    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            String uri = DBConfig.get("mongo.uri");
            mongoClient = MongoClients.create(uri);
        }
        String dbName = DBConfig.get("mongo.database");
        return mongoClient.getDatabase(dbName);
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
