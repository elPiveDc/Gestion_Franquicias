
package cargapgAdmin;

import com.opencsv.CSVReader;
import conexion.PostgresConnector;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class CargaPostgreSQLotesProduccion {
    
    public static void cargarCSV(String rutaArchivo) {
        String sql = "INSERT INTO lotes_produccion " +
                     "(id_lote, id_producto, fecha_produccion, fecha_vencimiento, cantidad_producida, ubicacion_actual) " +
                     "VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (id_lote) DO NOTHING";

        try (Connection conn = PostgresConnector.getConnection();
             FileReader fr = new FileReader(rutaArchivo);
             CSVReader reader = new CSVReader(fr);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Limpiar la tabla antes de insertar
//            conn.createStatement().execute("TRUNCATE TABLE lotes_produccion RESTART IDENTIT   Y CASCADE");

            String[] fila;
            reader.readNext(); // Saltar la cabecera

            while ((fila = reader.readNext()) != null) {
                if (fila.length >= 6) {
                        stmt.setInt(1, Integer.parseInt(fila[0]));
                        stmt.setInt(2, Integer.parseInt(fila[1]));
                        stmt.setDate(3, Date.valueOf(fila[2]));
                        stmt.setDate(4, Date.valueOf(fila[3]));
                        stmt.setInt(5, Integer.parseInt(fila[4]));
                        stmt.setString(6, fila[5]);
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

   
