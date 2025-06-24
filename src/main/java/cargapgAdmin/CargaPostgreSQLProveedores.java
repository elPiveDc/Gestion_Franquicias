package cargapgAdmin;

import com.opencsv.CSVReader;
import conexion.PostgresConnector;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CargaPostgreSQLProveedores {

    public static void cargarCSV(String rutaArchivo) {
        String sql = "INSERT INTO proveedores (nombre, direccion, telefono) VALUES (?, ?, ?)";

        try (Connection conn = PostgresConnector.getConnection(); 
                FileReader fr = new FileReader(rutaArchivo);
                CSVReader reader = new CSVReader(fr);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
          
            //  Limpiar la tabla antes de insertar //
            conn.createStatement().execute("TRUNCATE TABLE proveedores");

            String[] fila;
            reader.readNext(); // Saltar la cabecera (nombre,direccion,telefono)

            while ((fila = reader.readNext()) != null) {
                if (fila.length >= 3) { // Seguridad por si faltan campos
                    stmt.setString(1, fila[0]);
                    stmt.setString(2, fila[1]);
                    stmt.setString(3, fila[2]);
                    stmt.executeUpdate();
                } else {
                    System.out.println("Fila con datos incompletos: " + String.join(",", fila));
                }
            }

            System.out.println("Datos insertados correctamente en PostgreSQL.");
        } catch (Exception e) {
            System.err.println("Error al insertar datos en PostgreSQL:");
            e.printStackTrace();
        }
    }
    
    public static void mostrarProveedores() {
        String sql = "SELECT nombre, direccion, telefono FROM proveedores";

        try (
                Connection conn = PostgresConnector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n Lista de Proveedores:");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                System.out.println(nombre + " | " + direccion + " | " + telefono);
            }
        } catch (Exception e) {
            System.err.println(" Error al consultar proveedores:");
            e.printStackTrace();
        }
    }
}


