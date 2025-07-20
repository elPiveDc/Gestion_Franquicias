package com.MiNegocio.configuracioncentral.repository.impl;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, correo, password_hash, fecha_registro) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPasswordHash());
            ps.setTimestamp(4, Timestamp.valueOf(usuario.getFechaRegistro()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar usuario", e);
        }
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por ID", e);
        }

        return Optional.empty();
    }

    @Override
    public Usuario obtenerCreadorDeFranquicia(int idFranquicia) {
        String sql = """
        SELECT u.* FROM usuarios u
        JOIN franquicias f ON f.id_usuario = u.id_usuario
        WHERE f.id_franquicia = ?
    """;

        try (Connection conn = ConexionBDFactory.getConexion(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFranquicia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                return u;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo usuario creador", e);
        }

        throw new RuntimeException("No se encontró el usuario creador para la franquicia.");
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre"));
        u.setCorreo(rs.getString("correo"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
        return u;
    }
}
