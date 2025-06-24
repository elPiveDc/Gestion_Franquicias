package cargapgAdmin;
import conexion.PostgresConnector;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CargaPostgreSQLProductos {

    public static void cargarCSV(String rutaArchivo) {
        String sql = "INSERT INTO productos (id_producto, nombre, tipo, unidad_medida, "
                + "precio_unitario) VALUES (?, ?, ?, ?, ?) ON CONFLICT (id_producto) DO NOTHING";

        try (
            Connection conn = PostgresConnector.getConnection();
            FileReader fr = new FileReader(rutaArchivo);
            CSVReader reader = new CSVReader(fr);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Limpiar las tablas antes de insertar
//            conn.createStatement().execute("TRUNCATE TABLE lotes_produccion, productos");

            String[] fila;
            reader.readNext(); // Saltar la cabecera

            while ((fila = reader.readNext()) != null) {
                if (fila.length >= 5) {
                    stmt.setInt(1, Integer.parseInt(fila[0].trim())); // id_producto
                    stmt.setString(2, fila[1].trim()); // nombre
                    stmt.setString(3, fila[2].trim()); // tipo
                    stmt.setString(4, fila[3].trim()); // unidad_medida
                    stmt.setBigDecimal(5, new BigDecimal(fila[4].trim())); // precio_unitario
                    stmt.executeUpdate();
                } else {
                    System.out.println(" Fila con datos incompletos: " + String.join(",", fila));
                }
            }

            System.out.println(" Datos insertados correctamente en PostgreSQL.");
        } catch (Exception e) {
            System.err.println(" Error al insertar datos en PostgreSQL:");
            e.printStackTrace();
        }
    }
        
    public static void mostrarProductos() {
        String sql = "SELECT id_producto, nombre, tipo, unidad_medida, precio_unitario FROM productos";

        try (
            Connection conn = PostgresConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n Lista de Productos:");
            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                String unidad = rs.getString("unidad_medida");
                BigDecimal precio = rs.getBigDecimal("precio_unitario");
                System.out.println(id + " | " + nombre + " | " + tipo + " | " + unidad + " |  "+ precio);
            }
        } catch (Exception e) {
            System.err.println(" Error al consultar productos:");
            e.printStackTrace();
        }
    }
}
