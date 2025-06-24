package main;

import carga.CargaOracleProveedores;
import carga.CargaPostgreSQLProveedores;
import cargasMongo.CargaMongoOpiniones;
import cargasMongo.CargaMongoRecetas;
import com.mongodb.client.MongoDatabase;
import conexion.MongoConnector;

public class Main {
    public static void main(String[] args) {
    
        CargaOracleProveedores.cargarProveedores("src/main/resources/proveedores.csv");
        CargaOracleProveedores.cargarInsumos("src/main/resources/insumos.csv");
        CargaOracleProveedores.cargarOrdenesCompra("src/main/resources/ordenes_compra.csv");
        CargaOracleProveedores.cargarDetalleOrdenCompra("src/main/resources/detalle_orden_compra.csv");
        CargaOracleProveedores.cargarInspeccionesCalidad("src/main/resources/inspecciones_calidad.csv");
        
        MongoDatabase db = MongoConnector.getDatabase();
        System.out.println("Conectado a MongoDB, base: " + db.getName());
       
        
        CargaMongoOpiniones.cargarCSV("src/main/resources/opiniones.csv");
        CargaMongoRecetas.cargarJSON("src/main/resources/recetas.json");
    }
}