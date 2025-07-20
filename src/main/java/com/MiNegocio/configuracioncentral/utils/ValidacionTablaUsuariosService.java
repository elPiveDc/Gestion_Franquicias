package com.MiNegocio.configuracioncentral.utils;

import static com.MiNegocio.configuracioncentral.factory.ConexionBDFactory.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidacionTablaUsuariosService {

    public static boolean existeOtraTablaUsuariosEnFranquicia(Connection conn, int idBDActual) throws SQLException {
        String sql = """
            SELECT COUNT(*) AS total
            FROM objetos_bd_franquicia obj
            JOIN bases_datos_franquicia bd ON obj.id_bd = bd.id_bd
            WHERE bd.id_franquicia = (
                SELECT id_franquicia FROM bases_datos_franquicia WHERE id_bd = ?
            )
            AND obj.es_tabla_usuarios = TRUE
            AND obj.id_bd != ?;
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idBDActual);
            stmt.setInt(2, idBDActual);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }

        return false;
    }

    public static boolean existeOtraTablaUsuariosEnFranquiciaPorId(int idFranquicia) throws SQLException {
        String sql = """
        SELECT COUNT(*) AS total
        FROM objetos_bd_franquicia obj
        JOIN bases_datos_franquicia bd ON obj.id_bd = bd.id_bd
        WHERE bd.id_franquicia = ?
        AND obj.es_tabla_usuarios = TRUE;
    """;

        try (Connection conn = getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFranquicia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si hay más de una tabla de usuarios, retorna true
                    return rs.getInt("total") > 1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }

    public static boolean existeOtraTablaUsuariosEnFranquiciaPorNombre(String nombreFranquicia) throws SQLException {
        String sql = """
        SELECT COUNT(*) AS total
        FROM objetos_bd_franquicia obj
        JOIN bases_datos_franquicia bd ON obj.id_bd = bd.id_bd
        JOIN franquicias f ON bd.id_franquicia = f.id_franquicia
        WHERE f.nombre_franquicia = ?
        AND obj.es_tabla_usuarios = TRUE;
    """;

        try (Connection conn = getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreFranquicia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }

}
