package cargasMongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.MongoConnector;
import org.bson.Document;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargaMongoProveedores {

    public static void cargarCSV(String rutaArchivo) {
        try (
            FileReader fr = new FileReader(rutaArchivo);
            CSVReader reader = new CSVReader(fr)
        ) {
            MongoDatabase database = MongoConnector.getDatabase();
            MongoCollection<Document> coleccion = database.getCollection("proveedores");

            String[] fila;
            reader.readNext(); // Saltar cabecera (nombre,direccion,telefono)

            List<Document> documentos = new ArrayList<>();

            while ((fila = reader.readNext()) != null) {
                if (fila.length >= 3) {
                    Document doc = new Document("nombre", fila[0])
                                    .append("direccion", fila[1])
                                    .append("telefono", fila[2]);
                    documentos.add(doc);
                } else {
                    System.out.println("Fila incompleta: " + String.join(",", fila));
                }
            }

            if (!documentos.isEmpty()) {
                coleccion.insertMany(documentos);
                System.out.println("Datos insertados correctamente en MongoDB.");
            } else {
                System.out.println("No se insertó nada, el archivo estaba vacío o mal formado.");
            }

        } catch (Exception e) {
            System.err.println("Error al insertar datos en MongoDB:");
            e.printStackTrace();
        } finally {
            MongoConnector.closeConnection();
        }
    }
}
