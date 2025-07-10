package com.MiNegocio.configuracioncentral;

import com.MiNegocio.accesousuarios.autenticacion.GestorAutenticacionFranquicia;
import com.MiNegocio.accesousuarios.control.ControladorAccionesUsuario;
import com.MiNegocio.accesousuarios.control.SesionUsuarioFranquiciaController;
import com.MiNegocio.configuracioncentral.application.RegistroInicialService;
import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.factory.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.service.impl.BaseDatosServiceImpl;
import com.MiNegocio.configuracioncentral.service.impl.FranquiciaServiceImpl;
import com.MiNegocio.configuracioncentral.service.impl.ObjetoBDServiceImpl;
import com.MiNegocio.configuracioncentral.service.impl.UsuarioServiceImpl;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Repositorios
        UsuarioRepositoryImpl usuarioRepo = new UsuarioRepositoryImpl();
        FranquiciaRepositoryImpl franquiciaRepo = new FranquiciaRepositoryImpl();
        BaseDatosRepositoryImpl bdRepo = new BaseDatosRepositoryImpl();
        ObjetoBDRepositoryImpl objetoRepo = new ObjetoBDRepositoryImpl();

// Servicios
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioRepo);
        FranquiciaServiceImpl franquiciaService = new FranquiciaServiceImpl(franquiciaRepo, usuarioRepo);
        BaseDatosServiceImpl bdService = new BaseDatosServiceImpl(bdRepo, franquiciaRepo);
        GestorObjetosFactory gestorFactory = new GestorObjetosFactory();
        ConexionMultiBDFactory conexionFactory = new ConexionMultiBDFactory();
        ObjetoBDServiceImpl objetoService = new ObjetoBDServiceImpl(objetoRepo, bdRepo, conexionFactory, gestorFactory);

// Orquestador de registro
        RegistroInicialService registro = new RegistroInicialService(usuarioService, franquiciaService, bdService);

// ==== Usuario ====
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Luis Gamarra");
        nuevoUsuario.setCorreo("luis@franquicia.com");
        nuevoUsuario.setPasswordHash("admin1234");
        nuevoUsuario.setFechaRegistro(LocalDateTime.now());

// ==== Franquicia ====
        Franquicia nuevaFranquicia = new Franquicia();
        nuevaFranquicia.setNombre("Tech Café");
        nuevaFranquicia.setFechaCreacion(LocalDateTime.now());
        nuevaFranquicia.setEstado(EstadoFranquicia.ACTIVA);

// ==== Base de Datos MySQL ====
        BaseDatosFranquicia bdMySQL = new BaseDatosFranquicia();
        bdMySQL.setNombreBD("bd_mysql");
        bdMySQL.setTipo(TipoBD.MYSQL);
        bdMySQL.setEstado(EstadoBD.NO_CONFIGURADA);
        bdMySQL.setUrlConexion("jdbc:mysql://localhost:3306/bd_mysql");
        bdMySQL.setUsuarioBD("usuario_mysql");
        bdMySQL.setPasswordHash("mysql123");

// ==== Base de Datos PostgreSQL ====
        BaseDatosFranquicia bdPG = new BaseDatosFranquicia();
        bdPG.setNombreBD("bd_postgres");
        bdPG.setTipo(TipoBD.POSTGRESQL);
        bdPG.setEstado(EstadoBD.NO_CONFIGURADA);
        bdPG.setUrlConexion("jdbc:postgresql://localhost:5432/bd_postgres");
        bdPG.setUsuarioBD("usuario_pg");
        bdPG.setPasswordHash("pg123");


        // Crear tabla genérica en MySQL
        try {
            ObjetoBDFranquicia objMySQL = new ObjetoBDFranquicia();
            objMySQL.setNombreTabla("productos");
            objMySQL.setTipoObjeto("TABLA");
            objMySQL.setColumnas("""
                [ 
                    {"nombre": "id", "tipo": "entero", "restricciones": "PRIMARY KEY AUTO_INCREMENT"},
                    {"nombre": "nombre", "tipo": "cadena", "restricciones": "NOT NULL"}
                ]
            """);
        } catch (Exception e) {
            System.err.println("❌ Error al crear tabla en MySQL: " + e.getMessage());
        }

        // Crear tabla usuarios en PostgreSQL (la tabla que usaremos para login)
        try {
            ObjetoBDFranquicia objUsuariosPG = new ObjetoBDFranquicia();
            objUsuariosPG.setNombreTabla("usuarios");
            objUsuariosPG.setTipoObjeto("TABLA");
            objUsuariosPG.setColumnas("""
                [ 
                    {"nombre": "id", "tipo": "entero", "restricciones": "NOT NULL"},
                    {"nombre": "nombre_usuario", "tipo": "cadena", "restricciones": "NOT NULL"},
                    {"nombre": "password_hash", "tipo": "cadena", "restricciones": "NOT NULL"},
                    {"nombre": "es_admin", "tipo": "entero", "restricciones": "DEFAULT 0"}
                ]
            """);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // 🔐 Inicio de sesión
        System.out.println("\n========== INICIO DE SESIÓN EN FRANQUICIA ==========");
        String nombreFranquicia = "Tech Café";
        String usuario = "user1";
        String password = "123";

        GestorAutenticacionFranquicia gestor = new GestorAutenticacionFranquicia();
        boolean autenticado = gestor.autenticar(nombreFranquicia, usuario, password);

        if (autenticado) {
            System.out.println("✅ Usuario autenticado correctamente dentro de la franquicia.");

            // Obtenemos la sesión activa
            var sesion = SesionUsuarioFranquiciaController.getInstancia();

            // Mostrar menú
            var controlador = new ControladorAccionesUsuario(sesion);
            controlador.mostrarMenuAcciones();
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos o no registrado en ninguna BD.");
        }
    }
}
