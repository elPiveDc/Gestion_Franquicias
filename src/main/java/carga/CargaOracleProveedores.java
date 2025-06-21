package carga;

import com.opencsv.CSVReader;
import conexion.OracleConnector;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CargaOracleProveedores {

    public static void cargarCSV(String rutaArchivo) {
        String sql = "INSERT INTO proveedores (nombre, direccion, telefono) VALUES (?, ?, ?)";

        try (Connection conn = OracleConnector.getConnection();
             CSVReader reader = new CSVReader(new FileReader(rutaArchivo));
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String[] fila;
            reader.readNext(); // saltar cabecera

            while ((fila = reader.readNext()) != null) {
                stmt.setString(1, fila[0]);
                stmt.setString(2, fila[1]);
                stmt.setString(3, fila[2]);
                stmt.executeUpdate();
            }

            System.out.println("Datos insertados correctamente en Oracle.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
