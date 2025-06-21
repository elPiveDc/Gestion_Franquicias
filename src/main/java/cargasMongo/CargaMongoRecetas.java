package cargasMongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.MongoConnector;
import org.bson.Document;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CargaMongoRecetas {

    public static void cargarJSON(String rutaArchivo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> recetas = mapper.readValue(
                new File(rutaArchivo),
                new TypeReference<List<Map<String, Object>>>() {}
            );

            MongoDatabase db = MongoConnector.getDatabase();
            MongoCollection<Document> collection = db.getCollection("recetas");

            for (Map<String, Object> receta : recetas) {
                Document doc = new Document(receta);
                collection.insertOne(doc);
            }

            System.out.println("Recetas insertadas correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar recetas:");
            e.printStackTrace();
        }
    }
}
