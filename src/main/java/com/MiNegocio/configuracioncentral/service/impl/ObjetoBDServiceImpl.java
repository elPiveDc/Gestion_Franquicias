package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.repository.BaseDatosRepository;
import com.MiNegocio.configuracioncentral.repository.ObjetoBDRepository;
import com.MiNegocio.configuracioncentral.repository.UsuarioRepository;
import com.MiNegocio.configuracioncentral.service.IAService;
import com.MiNegocio.configuracioncentral.service.ObjetoBDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ObjetoBDServiceImpl implements ObjetoBDService {

    private final ObjetoBDRepository objetoBDRepository;
    private final BaseDatosRepository baseDatosRepository;
    private final UsuarioRepository usuarioRepository;
    private final GestorObjetosFactory gestorFactory;

    public ObjetoBDServiceImpl(
            ObjetoBDRepository repo,
            BaseDatosRepository bdRepo,
            UsuarioRepository usuarioRepo,
            ConexionMultiBDFactory factory,
            GestorObjetosFactory gestorFactory) {
        this.objetoBDRepository = repo;
        this.baseDatosRepository = bdRepo;
        this.usuarioRepository = usuarioRepo;
        this.gestorFactory = gestorFactory;
    }

    @Override
    public void crearObjeto(int idBD, ObjetoBDFranquicia objeto) throws Exception {
        
        BaseDatosFranquicia bd = baseDatosRepository.buscarPorId(idBD);

        objeto.setFechaCreacion(new Date());
        objeto.setIdBD(idBD);

        if (!objetoBDRepository.existeTablaUsuariosParaFranquicia(bd.getId_franquicia())) {

            int idBDSeleccionada = preguntarEnQueBDCrearUsuarios(bd.getId_franquicia());

            // Obtener el usuario creador
            Usuario creador = usuarioRepository.obtenerCreadorDeFranquicia(bd.getId_franquicia());

            // Crear la tabla e insertar al creador
            crearTablaUsuariosPorDefecto(idBDSeleccionada, creador);

            System.out.println("La tabla 'usuarios' fue creada correctamente con el usuario inicial.");
        }
        
        // Crear el objeto solicitado
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        
        gestor.crearObjeto(bd, objeto);
        objetoBDRepository.guardarObjetoBD(objeto);
        objetoBDRepository.guardarObjetoEnMongo(objeto);
    }

    public void crearObjetoConIA(int idBD, String consulta) throws Exception {
        
        ObjetoBDFranquicia objeto = new ObjetoBDFranquicia();
        BaseDatosFranquicia bd = baseDatosRepository.buscarPorId(idBD);

        objeto.setFechaCreacion(new Date());
        objeto.setIdBD(idBD);

        if (!objetoBDRepository.existeTablaUsuariosParaFranquicia(bd.getId_franquicia())) {

            int idBDSeleccionada = preguntarEnQueBDCrearUsuarios(bd.getId_franquicia());

            // Obtener el usuario creador
            Usuario creador = usuarioRepository.obtenerCreadorDeFranquicia(bd.getId_franquicia());

            // Crear la tabla e insertar al creador
            crearTablaUsuariosPorDefecto(idBDSeleccionada, creador);

            System.out.println("La tabla 'usuarios' fue creada correctamente con el usuario inicial.");
        }
        
        String json = IAService.generarSQLDesdePregunta(consulta + 
                "usa la confiiguracion de lengujae SQL para el tipo de bd: " + bd.getTipo());
        objeto.setColumnas(json);
        
        // Crear el objeto solicitado
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        gestor.crearObjeto(bd, objeto);
        objetoBDRepository.guardarObjetoBD(objeto);
        objetoBDRepository.guardarObjetoEnMongo(objeto);
    }
    

    private void crearTablaUsuariosPorDefecto(int idBD, Usuario creador) throws Exception {

        BaseDatosFranquicia bd = baseDatosRepository.buscarPorId(idBD);

        ObjetoBDFranquicia tablaUsuarios = new ObjetoBDFranquicia();
        tablaUsuarios.setNombreTabla("usuarios");
        tablaUsuarios.setTipoObjeto("TABLA");
        tablaUsuarios.setEsTablaUsuarios(true);
        tablaUsuarios.setIdBD(idBD);
        tablaUsuarios.setFechaCreacion(new Date());
        tablaUsuarios.setColumnas("""
        [
          {"nombre":"id_usuario", "tipo":"entero", "restricciones":"PRIMARY KEY"},
          {"nombre":"nombre_usuario", "tipo":"cadena", "restricciones":"NOT NULL"},
          {"nombre":"password_hash", "tipo":"cadena", "restricciones":"NOT NULL"},
          {"nombre":"es_admin", "tipo":"entero", "restricciones":"DEFAULT 1"}
        ]
        """);

        // Crear tabla
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        gestor.crearObjeto(bd, tablaUsuarios);

        // Registrar en objetos_bd_franquicia
        objetoBDRepository.guardarObjetoBD(tablaUsuarios);
        objetoBDRepository.guardarObjetoEnMongo(tablaUsuarios);

        // Insertar el usuario creador
        if (bd.getTipo().toString().equals("POSTGRESQL")) {
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {
                String insertSQL = """
                INSERT INTO usuarios (id_usuario, nombre_usuario, password_hash, es_admin)
                VALUES (?, ?, ?, ?)
            """;
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setInt(1, creador.getId());
                stmt.setString(2, creador.getCorreo()); // o getNombre() si quieres nombre
                stmt.setString(3, creador.getPasswordHash());
                stmt.setInt(4, 1); // es admin
                stmt.executeUpdate();
            }
        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd)) {
                String insertSQL = """
                INSERT INTO usuarios (id_usuario, nombre_usuario, password_hash, es_admin)
                VALUES (?, ?, ?, ?)
            """;
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setInt(1, creador.getId());
                stmt.setString(2, creador.getCorreo()); // o getNombre() si quieres nombre
                stmt.setString(3, creador.getPasswordHash());
                stmt.setInt(4, 1); // es admin
                stmt.executeUpdate();
            }
        }
    }

    private int preguntarEnQueBDCrearUsuarios(int idFranquicia) {
        List<BaseDatosFranquicia> bds = baseDatosRepository.buscarPorFranquicia(idFranquicia);

        System.out.println("Antes de continuar debes crear la tabla obligatoria 'usuarios'.");
        System.out.println("Selecciona en qué base de datos deseas crearla:");

        for (int i = 0; i < bds.size(); i++) {
            System.out.println((i + 1) + ". " + bds.get(i).getNombreBD() + " (" + bds.get(i).getTipo() + ")");
        }

        Scanner scanner = new Scanner(System.in);
        int seleccion = scanner.nextInt();
        return bds.get(seleccion - 1).getId();
    }
}
