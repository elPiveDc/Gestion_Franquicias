package carga;

import com.opencsv.CSVReader;
import conexion.OracleConnector;

import java.io.FileReader;
import java.sql.*;

public class CargaOracleProveedores {

    public static void cargarProveedores(String rutaArchivo) {
        String sql = "INSERT INTO proveedores (nombre, direccion, telefono, correo) VALUES (?, ?, ?, ?)";

        try (Connection conn = OracleConnector.getConnection(); 
                CSVReader reader = new CSVReader(new FileReader(rutaArchivo)); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            String[] fila;
            reader.readNext(); // saltar cabecera

            while ((fila = reader.readNext()) != null) {
                stmt.setString(1, fila[0]); // nombre
                stmt.setString(2, fila[1]); // direccion
                stmt.setString(3, fila[2]); // telefono
                stmt.setString(4, fila[3]); // correo
                stmt.executeUpdate();
            }

            System.out.println(" Datos insertados en 'proveedores'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int obtenerIdProveedor(Connection conn, String nombreProveedor) 
            throws SQLException {
        String sql = "SELECT id_proveedor FROM proveedores WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProveedor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Proveedor no encontrado: " + nombreProveedor);
            }
        }
    }

    public static void cargarInsumos(String rutaArchivo) {
        String sql = "INSERT INTO insumos (nombre, descripcion, unidad_medida, "
                + "fecha_ingreso, id_proveedor, calidad) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = OracleConnector.getConnection(); 
                CSVReader reader = new CSVReader(new FileReader(rutaArchivo)); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            String[] fila;
            reader.readNext(); // cabecera

            while ((fila = reader.readNext()) != null) {
                int idProveedor = obtenerIdProveedor(conn, fila[4]);

                stmt.setString(1, fila[0]); // nombre
                stmt.setString(2, fila[1]); // descripcion
                stmt.setString(3, fila[2]); // unidad_medida
                stmt.setDate(4, Date.valueOf(fila[3])); // fecha_ingreso
                stmt.setInt(5, idProveedor);
                stmt.setString(6, fila[5]); // calidad

                stmt.executeUpdate();
            }

            System.out.println("Datos insertados en 'insumos'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int obtenerIdProveedorPorNombre(Connection conn, String nombreProveedor) 
            throws SQLException {
        return obtenerIdProveedor(conn, nombreProveedor); // reutilizamos método anterior
    }

    public static void cargarOrdenesCompra(String rutaArchivo) {
        String sql = "INSERT INTO ordenes_compra (fecha, id_proveedor, estado) VALUES (?, ?, ?)";

        try (Connection conn = OracleConnector.getConnection(); 
                CSVReader reader = new CSVReader(new FileReader(rutaArchivo)); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            String[] fila;
            reader.readNext();

            while ((fila = reader.readNext()) != null) {
                int idProveedor = obtenerIdProveedorPorNombre(conn, fila[1]);

                stmt.setDate(1, Date.valueOf(fila[0])); // fecha
                stmt.setInt(2, idProveedor);            // id_proveedor
                stmt.setString(3, fila[2]);             // estado

                stmt.executeUpdate();
            }

            System.out.println(" Datos insertados en 'ordenes_compra'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int obtenerIdOrdenCompra(Connection conn, Date fecha) throws SQLException {
        String sql = "SELECT id_orden FROM ordenes_compra WHERE fecha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, fecha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Orden de compra no encontrada con fecha: " + fecha);
            }
        }
    }

    private static int obtenerIdInsumo(Connection conn, String nombreInsumo) throws SQLException {
        String sql = "SELECT id_insumo FROM insumos WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreInsumo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Insumo no encontrado: " + nombreInsumo);
            }
        }
    }

    public static void cargarDetalleOrdenCompra(String rutaArchivo) {
        String sql = "INSERT INTO detalle_orden_compra (id_orden, id_insumo, "
                + "cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = OracleConnector.getConnection(); 
                CSVReader reader = new CSVReader(new FileReader(rutaArchivo)); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            String[] fila;
            reader.readNext(); // cabecera

            while ((fila = reader.readNext()) != null) {
                Date fechaOrden = Date.valueOf(fila[0]);
                String nombreInsumo = fila[1];
                int cantidad = Integer.parseInt(fila[2]);
                double precio = Double.parseDouble(fila[3]);

                int idOrden = obtenerIdOrdenCompra(conn, fechaOrden);
                int idInsumo = obtenerIdInsumo(conn, nombreInsumo);

                stmt.setInt(1, idOrden);
                stmt.setInt(2, idInsumo);
                stmt.setInt(3, cantidad);
                stmt.setDouble(4, precio);
                stmt.executeUpdate();
            }

            System.out.println("Datos insertados en 'detalle_orden_compra'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cargarInspeccionesCalidad(String rutaArchivo) {
        String sql = "INSERT INTO inspecciones_calidad (id_insumo, fecha_inspeccion, "
                + "resultado, observaciones) VALUES (?, ?, ?, ?)";

        try (Connection conn = OracleConnector.getConnection(); 
                CSVReader reader = new CSVReader(new FileReader(rutaArchivo)); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            String[] fila;
            reader.readNext(); // cabecera

            while ((fila = reader.readNext()) != null) {
                String nombreInsumo = fila[0];
                Date fechaInspeccion = Date.valueOf(fila[1]);
                String resultado = fila[2];
                String observaciones = fila[3];

                int idInsumo = obtenerIdInsumo(conn, nombreInsumo);

                stmt.setInt(1, idInsumo);
                stmt.setDate(2, fechaInspeccion);
                stmt.setString(3, resultado);
                stmt.setString(4, observaciones);
                stmt.executeUpdate();
            }

            System.out.println("Datos insertados en 'inspecciones_calidad'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
