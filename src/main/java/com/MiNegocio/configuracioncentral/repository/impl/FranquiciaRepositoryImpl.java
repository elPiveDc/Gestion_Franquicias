package com.MiNegocio.configuracioncentral.repository.impl;

import com.MiNegocio.configuracioncentral.domain.EstadoFranquicia;
import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.repository.FranquiciaRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FranquiciaRepositoryImpl implements FranquiciaRepository {

    @Override
    public void guardar(Franquicia franquicia) {
        String sql = "INSERT INTO franquicias(nombre_franquicia, fecha_creacion, estado, id_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, franquicia.getNombre());
            ps.setTimestamp(2, Timestamp.valueOf(franquicia.getFechaCreacion()));
            ps.setString(3, franquicia.getEstado().name());
            ps.setInt(4, franquicia.getPropietario().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                franquicia.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar franquicia", e);
        }
    }

    @Override
    public List<Franquicia> obtenerPorUsuario(int idUsuario) {
        List<Franquicia> franquicias = new ArrayList<>();
        String sql = "SELECT * FROM franquicias WHERE id_usuario = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Franquicia f = mapear(rs);
                franquicias.add(f);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar franquicias", e);
        }

        return franquicias;
    }

    @Override
    public Franquicia buscarPorId(int id) {
        String sql = "SELECT * FROM franquicias WHERE id_franquicia = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar franquicia", e);
        }

        return null;
    }

    private Franquicia mapear(ResultSet rs) throws SQLException {
        Franquicia f = new Franquicia();
        f.setId(rs.getInt("id_franquicia"));
        f.setNombre(rs.getString("nombre_franquicia"));
        f.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        f.setEstado(EstadoFranquicia.valueOf(rs.getString("estado")));

        Usuario propietario = new Usuario();
        propietario.setId(rs.getInt("id_usuario"));
        f.setPropietario(propietario); // Carga mínima del usuario

        return f;
    }

    @Override
    public boolean existeNombreFranquicia(String nombre) {
        String sql = "SELECT COUNT(*) FROM franquicias WHERE nombre_franquicia = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de franquicia", e);
        }

        return false;
    }

}
