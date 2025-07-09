package com.MiNegocio.accesousuarios.autenticacion;

import com.MiNegocio.accesousuarios.control.SesionUsuarioFranquiciaController;
import com.MiNegocio.accesousuarios.model.ConexionFranquicia;
import com.MiNegocio.accesousuarios.model.UsuarioFranquicia;
import com.MiNegocio.accesousuarios.util.ConexionFranquiciaFactory;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.domain.TipoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAutenticacionFranquicia {

    public boolean autenticar(String nombreFranquicia, String usuario, String passwordHash) {
        try (Connection connCentral = ConexionBDFactory.getConexion()) {
            // 1. Obtener todas las bases de datos asociadas a la franquicia
            List<ConexionFranquicia> conexiones = obtenerBDsDeFranquicia(connCentral, nombreFranquicia);

            for (ConexionFranquicia info : conexiones) {
                String tablaUsuarios = obtenerTablaUsuarios(connCentral, info.getIdBD());
                if (tablaUsuarios != null) {
                    try (Connection conn = ConexionFranquiciaFactory.crearConexion(info)) {
                        // 2. Intentar autenticar
                        if (verificarUsuarioEnTabla(conn, tablaUsuarios, usuario, passwordHash)) {
                            boolean esAdmin = verificarSiEsAdmin(conn, tablaUsuarios, usuario);
                            UsuarioFranquicia user = new UsuarioFranquicia(usuario, info.getNombreBD(), info.getTipoBD(), esAdmin);
                            SesionUsuarioFranquiciaController.getInstancia().iniciarSesion(user);
                            return true;
                        }
                    } catch (SQLException e) {
                        System.err.println("Error al conectar con la BD de franquicia: " + info.getNombreBD());
                        e.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la autenticación desde la base central", e);
        }

        return false; // No autenticado
    }

    private List<ConexionFranquicia> obtenerBDsDeFranquicia(Connection connCentral, String nombreFranquicia) throws SQLException {
        String sql = """
            SELECT bdf.id_bd, bdf.nombre_bd, bdf.tipo_bd, bdf.url_conexion, bdf.usuario_conexion, bdf.pass_conexion_hash
            FROM bases_datos_franquicia bdf
            JOIN franquicias f ON bdf.id_franquicia = f.id_franquicia
            WHERE f.nombre_franquicia = ?
        """;

        try (PreparedStatement stmt = connCentral.prepareStatement(sql)) {
            stmt.setString(1, nombreFranquicia);
            ResultSet rs = stmt.executeQuery();

            List<ConexionFranquicia> lista = new ArrayList<>();
            while (rs.next()) {
                ConexionFranquicia cf = new ConexionFranquicia(
                        rs.getInt("id_bd"),
                        rs.getString("nombre_bd"),
                        TipoBD.valueOf(rs.getString("tipo_bd")),
                        rs.getString("url_conexion"),
                        rs.getString("usuario_conexion"),
                        rs.getString("pass_conexion_hash")
                );
                lista.add(cf);
            }
            return lista;
        }
    }

    private String obtenerTablaUsuarios(Connection connCentral, int idBD) throws SQLException {
        String sql = """
            SELECT nombre_tabla FROM objetos_bd_franquicia
            WHERE id_bd = ? AND es_tabla_usuarios = TRUE
            LIMIT 1
        """;

        try (PreparedStatement stmt = connCentral.prepareStatement(sql)) {
            stmt.setInt(1, idBD);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_tabla");
            }
        }
        return null;
    }

    private boolean verificarUsuarioEnTabla(Connection conn, String tabla, String usuario, String passwordHash) throws SQLException {
        String sql = "SELECT 1 FROM " + tabla + " WHERE nombre_usuario = ? AND password_hash = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    private boolean verificarSiEsAdmin(Connection conn, String tabla, String usuario) throws SQLException {
        String sql = "SELECT es_admin FROM " + tabla + " WHERE nombre_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("es_admin");
            }
        }
        return false;
    }
}
