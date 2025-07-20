package com.MiNegocio.configuracioncentral.repository.impl;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.EstadoBD;
import com.MiNegocio.configuracioncentral.domain.TipoBD;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.integration.basedatos.GestorCreacionBD;
import com.MiNegocio.configuracioncentral.integration.basedatos.GestorCreacionBDFactory;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.FranquiciaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDatosRepositoryImpl implements BaseDatosRepository {

    public BaseDatosRepositoryImpl() {
        this.franquiciaRepository = new FranquiciaRepositoryImpl();
    }

    private FranquiciaRepository franquiciaRepository;

    public void guardar(BaseDatosFranquicia bd, int idFranquicia) {
        var franquicia = franquiciaRepository.buscarPorId(idFranquicia);
        if (franquicia == null) {
            throw new IllegalArgumentException("Franquicia no encontrada");
        }
        try {
            GestorCreacionBD gestor = GestorCreacionBDFactory.getGestor(bd.getTipo().name());
            gestor.crearBaseDatos(bd);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la base de datos en el servidor: " + e.getMessage(), e);
        }
        bd.setEstado(EstadoBD.CONFIGURADA);
        String sql = "INSERT INTO bases_datos_franquicia(nombre_bd, tipo_bd, estado, url_conexion, usuario_conexion, pass_conexion_hash, id_franquicia) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBDFactory.getConexion(); 
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bd.getNombreBD());
            ps.setString(2, bd.getTipo().name());
            ps.setString(3, bd.getEstado().name());
            ps.setString(4, bd.getUrlConexion());
            ps.setString(5, bd.getUsuarioBD());
            ps.setString(6, bd.getPasswordHash());
            ps.setInt(7, bd.getId()); // Este es el ID de la franquicia

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bd.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar base de datos de franquicia", e);
        }
    }

    @Override
    public void guardar(BaseDatosFranquicia bd) {
        String sql = "INSERT INTO bases_datos_franquicia(nombre_bd, tipo_bd, estado, url_conexion, usuario_conexion, pass_conexion_hash, id_franquicia) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bd.getNombreBD());
            ps.setString(2, bd.getTipo().name());
            ps.setString(3, bd.getEstado().name());
            ps.setString(4, bd.getUrlConexion());
            ps.setString(5, bd.getUsuarioBD());
            ps.setString(6, bd.getPasswordHash());
            ps.setInt(7, bd.getId()); // Este es el ID de la franquicia

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bd.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar base de datos de franquicia", e);
        }
    }

    @Override
    public List<BaseDatosFranquicia> obtenerPorFranquicia(int idFranquicia) {
        List<BaseDatosFranquicia> lista = new ArrayList<>();
        String sql = "SELECT * FROM bases_datos_franquicia WHERE id_franquicia = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idFranquicia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar bases de datos", e);
        }

        return lista;
    }


    @Override
    public BaseDatosFranquicia buscarPorId(int id) {

        String sql = "SELECT * FROM bases_datos_franquicia WHERE id_bd = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar base de datos", e);
        }

        return null;
    }

    private BaseDatosFranquicia mapear(ResultSet rs) throws SQLException {
        BaseDatosFranquicia bd = new BaseDatosFranquicia();
        bd.setId(rs.getInt("id_bd"));
        bd.setId_franquicia(rs.getInt("id_franquicia"));
        bd.setNombreBD(rs.getString("nombre_bd"));
        bd.setTipo(TipoBD.valueOf(rs.getString("tipo_bd")));
        bd.setEstado(EstadoBD.valueOf(rs.getString("estado")));
        bd.setUrlConexion(rs.getString("url_conexion"));
        bd.setUsuarioBD(rs.getString("usuario_conexion"));
        bd.setPasswordHash(rs.getString("pass_conexion_hash"));
        return bd;
    }

    @Override
    public List<BaseDatosFranquicia> buscarPorFranquicia(int idFranquicia) {
        List<BaseDatosFranquicia> lista = new ArrayList<>();

        String sql = """
        SELECT id_bd, nombre_bd, tipo_bd, estado, url_conexion, usuario_conexion, pass_conexion_hash, id_franquicia
        FROM bases_datos_franquicia
        WHERE id_franquicia = ?
    """;

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFranquicia);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BaseDatosFranquicia bd = new BaseDatosFranquicia();
                bd.setId(rs.getInt("id_bd"));
                bd.setNombreBD(rs.getString("nombre_bd"));
                bd.setTipo(TipoBD.valueOf(rs.getString("tipo_bd")));
                bd.setEstado(EstadoBD.valueOf(rs.getString("estado")));
                bd.setUrlConexion(rs.getString("url_conexion"));
                bd.setUsuarioBD(rs.getString("usuario_conexion"));
                bd.setPasswordHash(rs.getString("pass_conexion_hash"));
                bd.setId_franquicia(rs.getInt("id_franquicia"));

                lista.add(bd);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar BDs por franquicia", e);
        }

        return lista;
    }

}
