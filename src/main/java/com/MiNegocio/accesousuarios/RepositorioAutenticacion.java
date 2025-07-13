package com.MiNegocio.accesousuarios;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.TipoBD;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositorioAutenticacion {

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

    public UsuarioFranquicia verificarCredenciales(BaseDatosFranquicia bd, String correo, String passwordHash) throws Exception {

        if (bd.getTipo().toString() == "POSTGRESQL") {
            
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {

                String sql = construirSQLAutenticacion(bd.getTipo());

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, correo);
                    stmt.setString(2, passwordHash);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        return new UsuarioFranquicia(
                                rs.getInt("id_usuario"),
                                rs.getString("nombre_usuario"),
                                rs.getInt("es_admin") == 1
                        );
                    }
                }
            }
        }
        try (Connection conn = ConexionMultiBDFactory.getConexion(bd)) {

            String sql = construirSQLAutenticacion(bd.getTipo());

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, correo);
                stmt.setString(2, passwordHash);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new UsuarioFranquicia(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre_usuario"),
                            rs.getInt("es_admin") == 1
                    );
                }
            }
        }
        return null;
    }

    private String construirSQLAutenticacion(TipoBD tipoBD) {
        // Misma consulta para todos los tipos por ahora
        switch (tipoBD) {
            case MYSQL:
            case POSTGRESQL:
            case ORACLE:
                return """
                       SELECT id_usuario, nombre_usuario, es_admin
                       FROM usuarios
                       WHERE nombre_usuario = ? AND password_hash = ?
                       """;
            default:
                throw new UnsupportedOperationException("Tipo de BD no soportado: " + tipoBD);
        }
    }
}
