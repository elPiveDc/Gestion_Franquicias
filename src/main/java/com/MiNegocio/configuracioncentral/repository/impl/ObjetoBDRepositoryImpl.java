package com.MiNegocio.configuracioncentral.repository.impl;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.factory.ConexionNoSQL;
import com.MiNegocio.configuracioncentral.repository.ObjetoBDRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.JSONArray;

public class ObjetoBDRepositoryImpl implements ObjetoBDRepository {

    @Override
    public void guardarObjetoBD(ObjetoBDFranquicia objeto) {

        String sql = "INSERT INTO objetos_bd_franquicia "
                + "(id_bd, nombre_tabla, tipo_objeto, es_tabla_usuarios, columnas, fecha_creacion) "
                + "VALUES( ?,  ?,  ?,  ?,  ?,  ?)";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, objeto.getIdBD());
            ps.setString(2, objeto.getNombreTabla());
            ps.setString(3, objeto.getTipoObjeto());
            ps.setBoolean(4, objeto.isEsTablaUsuarios());
            ps.setString(5, objeto.getColumnas());
            ps.setTimestamp(6, new Timestamp(objeto.getFechaCreacion().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el objeto en la base de datos central", e);
        }
    }

    @Override
    public void guardarObjetoEnMongo(ObjetoBDFranquicia objeto) {
        try {
            MongoClient mongoClient = ConexionNoSQL.getMongoConexion();

            MongoDatabase db = mongoClient.getDatabase("bdcentral");
            MongoCollection<Document> coleccion = db.getCollection("objetos_bd_franquicia");

            List<Document> columnasList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(objeto.getColumnas());

            for (int i = 0; i < jsonArray.length(); i++) {
                columnasList.add(Document.parse(jsonArray.getJSONObject(i).toString()));
            }

            // Crea el documento principal para guardar
            Document doc = new Document()
                    .append("id_bd", objeto.getIdBD())
                    .append("nombre_tabla", objeto.getNombreTabla())
                    .append("tipo_objeto", objeto.getTipoObjeto())
                    .append("es_tabla_usuarios", objeto.isEsTablaUsuarios())
                    .append("columnas", columnasList)
                    .append("fecha_creacion", objeto.getFechaCreacion() != null ? objeto.getFechaCreacion() : new Date());

            // Inserta el documento en Mongo
            coleccion.insertOne(doc);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el objeto en MongoDB", e);
        }
    }

    @Override
    public List<ObjetoBDFranquicia> listarObjetosPorBD(int idBD) {
        
        List<ObjetoBDFranquicia> lista = new ArrayList<>();
        String sql = "SELECT * FROM objetos_bd_franquicia WHERE id_bd = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBD);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearObjeto(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar objetos de la BD " + idBD, e);
        }

        return lista;
    }

    @Override
    public ObjetoBDFranquicia buscarObjetoPorNombre(String nombreTabla, int idBD) {
        String sql = "SELECT * FROM objetos_bd_franquicia WHERE nombre_tabla = ? AND id_bd = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreTabla);
            ps.setInt(2, idBD);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearObjeto(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el objeto '" + nombreTabla + "' en la BD " + idBD, e);
        }

        return null;
    }

    @Override
    public boolean existeTablaUsuarios(int idBD) {
        String sql = "SELECT 1 FROM objetos_bd_franquicia WHERE id_bd = ? AND es_tabla_usuarios = TRUE LIMIT 1";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idBD);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existeTablaUsuariosParaFranquicia(int idFranquicia) {
        String sql = """
        SELECT 1 FROM objetos_bd_franquicia ob
        JOIN bases_datos_franquicia bd ON ob.id_bd = bd.id_bd
        WHERE bd.id_franquicia = ? AND ob.es_tabla_usuarios = TRUE
        LIMIT 1
    """;

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFranquicia);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error verificando si ya existe la tabla usuarios para la franquicia", e);
        }
    }

    private ObjetoBDFranquicia mapearObjeto(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        System.out.println("Columnas disponibles en el ResultSet:");
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.println("- " + meta.getColumnName(i));
        }

        ObjetoBDFranquicia obj = new ObjetoBDFranquicia();
        obj.setIdObjeto(rs.getInt("id_objeto"));
        obj.setIdBD(rs.getInt("id_bd"));
        obj.setNombreTabla(rs.getString("nombre_tabla"));
        obj.setTipoObjeto(rs.getString("tipo_objeto"));
        obj.setEsTablaUsuarios(rs.getBoolean("es_tabla_usuarios"));
        obj.setColumnas((String) rs.getObject("columnas"));
        obj.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        return obj;
    }

    @Override
    public ObjetoBDFranquicia buscarPorId(int idObjeto) {
        String sql = "SELECT * FROM objetos_bd_franquicia WHERE id_objeto = ?";

        try (Connection conn = ConexionBDFactory.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idObjeto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearObjeto(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el objeto con ID " + idObjeto, e);
        }

        return null;
    }

}
