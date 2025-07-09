package com.MiNegocio.accesousuarios.repositorio;

import com.MiNegocio.accesousuarios.model.ConexionFranquicia;
import com.MiNegocio.configuracioncentral.domain.TipoBD;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioBasesDatosFranquicia {

    public List<ConexionFranquicia> obtenerBasesDeFranquicia(String nombreFranquicia) {
        List<ConexionFranquicia> listaConexiones = new ArrayList<>();

        String sql = """
            SELECT bd.id_bd, bd.nombre_bd, bd.tipo_bd, bd.url_conexion, bd.usuario_conexion, bd.pass_conexion_hash
            FROM bases_datos_franquicia bd
            JOIN franquicias f ON bd.id_franquicia = f.id_franquicia
            WHERE f.nombre_franquicia = ?
            AND bd.estado = 'CONFIGURADA'
        """;

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreFranquicia);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idBD = rs.getInt("id_bd");
                    String nombreBD = rs.getString("nombre_bd");
                    TipoBD tipoBD = TipoBD.valueOf(rs.getString("tipo_bd"));
                    String url = rs.getString("url_conexion");
                    String usuario = rs.getString("usuario_conexion");
                    String password = rs.getString("pass_conexion_hash");

                    ConexionFranquicia conexion = new ConexionFranquicia(
                            idBD, nombreBD, tipoBD, url, usuario, password
                    );
                    listaConexiones.add(conexion);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las bases de datos de la franquicia: " + e.getMessage(), e);
        }

        return listaConexiones;
    }
}