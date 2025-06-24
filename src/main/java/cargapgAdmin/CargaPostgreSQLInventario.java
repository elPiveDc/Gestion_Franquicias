
package cargapgAdmin;

import com.opencsv.CSVReader;
import conexion.PostgresConnector;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.math.BigDecimal;

public class CargaPostgreSQLInventario {
     public static void cargarCSV(String rutaArchivo) {
        String sql = "INSERT INTO inventario " 
                + " (id_inventario, id_producto, id_lote, cantidad_disponible, ubicacion, fecha_entrada )"+
            " VALUES (?, ?, ?, ?, ?, ?)";
                     
        
        try (Connection conn = PostgresConnector.getConnection();
             FileReader fr = new FileReader(rutaArchivo);
             CSVReader reader = new CSVReader(fr);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
//             conn.createStatement().execute("TRUNCATE TABLE inventario RESTART IDENTITY CASCADE");
             
            String[] fila;
            reader.readNext(); // Saltar la cabecera

            while ((fila = reader.readNext()) != null) {
                if (fila.length >= 6) {
                        
                    stmt.setInt(1, Integer.parseInt(fila[0])); // id_inventario
                    stmt.setInt(2, Integer.parseInt(fila[1])); // id_producto
                    stmt.setInt(3, Integer.parseInt(fila[2])); // id_lote
                    stmt.setBigDecimal(4, new BigDecimal(fila[3])); // cantidad_disponible
                    stmt.setString(5, fila[4]); // ubicación
                    stmt.setDate(6, Date.valueOf(fila[5])); // fecha_entrada
                    stmt.executeUpdate();

                        } else {
                    System.err.println("Fila con datos incompletos: " + String.join(",", fila));
                }
            }
             System.out.println(" Datos insertados correctamente en PostgreSQL.");

        } catch (Exception e) {
            System.err.println("Error al cargar el archivo CSV:");
            e.printStackTrace();
        }
     }
}
