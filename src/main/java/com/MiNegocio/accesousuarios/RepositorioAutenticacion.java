package com.MiNegocio.accesousuarios;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.TipoBD;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioAutenticacion {

    public boolean existeFranquicia(String nombreFranquicia) {
        String query = "SELECT 1 FROM franquicias WHERE nombre_franquicia = ? LIMIT 1";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreFranquicia);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public BaseDatosFranquicia obtenerBDUsuariosPorFranquicia(String nombreFranquicia) throws Exception {
        String query = """
            SELECT 
                bd.id_bd, bd.id_franquicia, bd.nombre_bd, bd.tipo_bd, bd.estado,
                bd.url_conexion, bd.usuario_conexion, bd.pass_conexion_hash
            FROM franquicias f
            JOIN bases_datos_franquicia bd ON f.id_franquicia = bd.id_franquicia
            JOIN objetos_bd_franquicia obj ON bd.id_bd = obj.id_bd
            WHERE f.nombre_franquicia = ? 
              AND obj.es_tabla_usuarios = TRUE
            LIMIT 1
        """;

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreFranquicia);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new BaseDatosFranquicia(
                        rs.getInt("id_bd"),
                        rs.getInt("id_franquicia"),
                        rs.getString("nombre_bd"),
                        TipoBD.valueOf(rs.getString("tipo_bd")),
                        null, // estado no mapeado aquí
                        rs.getString("url_conexion"),
                        rs.getString("usuario_conexion"),
                        rs.getString("pass_conexion_hash")
                );
            }
            return null;
        }
    }

    public UsuarioFranquicia verificarCredenciales(BaseDatosFranquicia bd, String correo, String password) throws Exception {
        String sql = """
        SELECT id_usuario, nombre_usuario, es_admin, password_hash
        FROM usuarios
        WHERE nombre_usuario = ?
        """;
        if ("POSTGRESQL".equals(bd.getTipo().toString())) {
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL",bd.getUrlConexion());
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, correo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashEnBD = rs.getString("password_hash");

                    if (PasswordUtils.verificar(password, hashEnBD)) {
                        return new UsuarioFranquicia(
                                rs.getInt("id_usuario"),
                                rs.getString("nombre_usuario"),
                                rs.getInt("es_admin") == 1
                        );
                    }
                }
            }
        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd);
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, correo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashEnBD = rs.getString("password_hash");

                    // Aquí validamos la contraseña en Java con BCrypt
                    if (PasswordUtils.verificar(password, hashEnBD)) {
                        return new UsuarioFranquicia(
                                rs.getInt("id_usuario"),
                                rs.getString("nombre_usuario"),
                                rs.getInt("es_admin") == 1
                        );
                    }
                }
            }
        }
        return null;
    }
}
