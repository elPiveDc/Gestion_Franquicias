package cargasMongo;

import com.opencsv.CSVReader;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.MongoConnector;
import org.bson.Document;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CargaMongoOpiniones {

    public static void cargarCSV(String rutaArchivo) {
        try (
            FileReader fr = new FileReader(rutaArchivo);
            CSVReader reader = new CSVReader(fr)
        ) {
            MongoDatabase db = MongoConnector.getDatabase();
            MongoCollection<Document> collection = db.getCollection("opiniones");

            String[] fila;
            reader.readNext();

            while ((fila = reader.readNext()) != null) {
                if (fila.length >= 5) {
                    String fechaActual = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

                    Document doc = new Document("cliente_id", fila[0])
                        .append("nombre", fila[1])
                        .append("fecha", fechaActual)
                        .append("comentario", fila[2])
                        .append("calificacion", Integer.parseInt(fila[3]))
                        .append("plato", fila[4]);

                    collection.insertOne(doc);
                } else {
                    System.out.println("Fila incompleta: " + String.join(",", fila));
                }
            }

            System.out.println("Opiniones insertadas correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar opiniones:");
            e.printStackTrace();
        }
    }
}
