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
import com.MiNegocio.configuracioncentral.utils.PasswordUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

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
        ObjetoBDServiceImpl objetoService = new ObjetoBDServiceImpl(objetoRepo, bdRepo,usuarioRepo, conexionFactory, gestorFactory);

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

        BaseDatosFranquicia bdoracle = new BaseDatosFranquicia();
        bdoracle.setNombreBD("");
        bdoracle.setTipo(TipoBD.ORACLE);
        bdoracle.setEstado(EstadoBD.NO_CONFIGURADA);
        bdoracle.setUrlConexion("jdbc:oracle:thin:@localhost:1521/XEPDB1");
        bdoracle.setUsuarioBD("usuario_oracle");
        bdoracle.setPasswordHash("pg123");

        

        ObjetoBDFranquicia tablaMySQL = new ObjetoBDFranquicia();
        tablaMySQL.setNombreTabla("productoswapos");
        tablaMySQL.setTipoObjeto("TABLA");
        tablaMySQL.setEsTablaUsuarios(false);
        tablaMySQL.setColumnas("""
[
  {"nombre":"id_producto","tipo":"entero","restricciones":"PRIMARY KEY"},
  {"nombre":"nombre","tipo":"cadena","restricciones":"NOT NULL"},
  {"nombre":"precio","tipo":"entero"},
  {"nombre":"fecha_ingreso","tipo":"fecha"}
]
""");
        tablaMySQL.setFechaCreacion(new Date());

        ObjetoBDFranquicia tablaPostgre = new ObjetoBDFranquicia();
        tablaPostgre.setNombreTabla("clientesMoracalientes");
        tablaPostgre.setTipoObjeto("TABLA");
        tablaPostgre.setEsTablaUsuarios(false);
        tablaPostgre.setColumnas("""
[
  {"nombre":"id_cliente","tipo":"entero","restricciones":"PRIMARY KEY"},
  {"nombre":"nombre","tipo":"cadena","restricciones":"NOT NULL"},
  {"nombre":"email","tipo":"cadena"},
  {"nombre":"fecha_registro","tipo":"fecha"}
]
""");
        tablaPostgre.setFechaCreacion(new Date());

        ObjetoBDFranquicia tablaOracle = new ObjetoBDFranquicia();
        tablaOracle.setNombreTabla("empleadosDoraforte");
        tablaOracle.setTipoObjeto("TABLA");
        tablaOracle.setEsTablaUsuarios(false);
        tablaOracle.setColumnas("""
[
  {"nombre":"id_empleado","tipo":"entero","restricciones":"PRIMARY KEY"},
  {"nombre":"nombre","tipo":"cadena","restricciones":"NOT NULL"},
  {"nombre":"cargo","tipo":"cadena"},
  {"nombre":"fecha_contratacion","tipo":"fecha"}
]
""");
        tablaOracle.setFechaCreacion(new Date());

        PasswordUtils p = new PasswordUtils();
        // Simulando hash por ahora (en el sistema real deberías usar un hash)
        String passwordHash = p.hashear("admin1234"); 

        GestorAutenticacionFranquicia gestor = new GestorAutenticacionFranquicia();
        boolean exito = gestor.autenticar("Tech Café", "luis@franquicia.com",passwordHash);

        if (exito) {
            ControladorAccionesUsuario controlador = new ControladorAccionesUsuario(SesionUsuarioFranquiciaController.getInstancia());
            controlador.mostrarMenuAcciones();
        } else {
            System.out.println("Autenticación fallida. Verifica usuario o contraseña.");
        }
        

    }
}
