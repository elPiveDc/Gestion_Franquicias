package main;

import carga.CargaOracleProveedores;
import carga.CargaPostgreSQLProveedores;
import cargasMongo.CargaMongoOpiniones;
import cargasMongo.CargaMongoRecetas;
import com.mongodb.client.MongoDatabase;
import conexion.MongoConnector;

public class Main {
    public static void main(String[] args) {
        
        MongoDatabase db = MongoConnector.getDatabase();
        System.out.println("Conectado a MongoDB, base: " + db.getName());
       
        
        CargaMongoOpiniones.cargarCSV("src/main/resources/opiniones.csv");
        CargaMongoRecetas.cargarJSON("src/main/resources/recetas.json");
    }
}
