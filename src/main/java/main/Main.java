package main;

import cargaOracle.CargaOracleProveedores;
import cargapgAdmin.CargaPostgreSQLInventario;
import cargapgAdmin.CargaPostgreSQLProductos;
import cargapgAdmin.CargaPostgreSQLProveedores;
import cargapgAdmin.CargaPostgreSQLotesProduccion;
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
        
        /*CargaOracleProveedores.cargarCSV("src/main/resources/proveedores.csv");*/
        CargaPostgreSQLProveedores.cargarCSV("src/main/resources/proveedores.csv");
        CargaPostgreSQLProveedores.mostrarProveedores();
        
        System.out.println("");
        System.out.println("Tabla productos");
        CargaPostgreSQLProductos.cargarCSV("src/main/resources/productos.csv");
        CargaPostgreSQLProductos.mostrarProductos();
        
        System.out.println("");
          System.out.println("Tabla lotes");
        CargaPostgreSQLotesProduccion.cargarCSV("src/main/resources/lotes_produccion.csv");
        
        System.out.println("");
        System.out.println("Tabla inventario");
        CargaPostgreSQLInventario.cargarCSV("src/main/resources/inventario.csv");
    }
}