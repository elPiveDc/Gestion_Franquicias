package com.MiNegocio.configuracioncentral;

import com.MiNegocio.accesousuarios.autenticacion.GestorAutenticacionFranquicia;
import com.MiNegocio.accesousuarios.control.ControladorAccionesUsuario;
import com.MiNegocio.accesousuarios.control.SesionUsuarioFranquiciaController;
import com.MiNegocio.configuracioncentral.application.RegistroInicialService;
import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.service.impl.*;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        
        /*
        // Repositorios
        var usuarioRepo = new UsuarioRepositoryImpl();
        var franquiciaRepo = new FranquiciaRepositoryImpl();
        var bdRepo = new BaseDatosRepositoryImpl();

        // Servicios
        var usuarioService = new UsuarioServiceImpl(usuarioRepo);
        var franquiciaService = new FranquiciaServiceImpl(franquiciaRepo, usuarioRepo);
        var bdService = new BaseDatosServiceImpl(bdRepo, franquiciaRepo);

        // Orquestador
        var registro = new RegistroInicialService(usuarioService, franquiciaService, bdService);

        // Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Luis Gamarra Valentin");
        nuevoUsuario.setCorreo("luis@franquicia3.com");
        nuevoUsuario.setPasswordHash("admin1234");
        nuevoUsuario.setFechaRegistro(LocalDateTime.now());

        // Franquicia
        Franquicia nuevaFranquicia = new Franquicia();
        nuevaFranquicia.setNombre("Tech Café3");
        nuevaFranquicia.setFechaCreacion(LocalDateTime.now());
        nuevaFranquicia.setEstado(EstadoFranquicia.ACTIVA);

        BaseDatosFranquicia bdMySQL = new BaseDatosFranquicia();
        bdMySQL.setNombreBD("bd_tech_mysqlA");
        bdMySQL.setTipo(TipoBD.MYSQL);
        bdMySQL.setEstado(EstadoBD.NO_CONFIGURADA);
        bdMySQL.setUrlConexion("jdbc:mysql://localhost:3306/bd_tech_mysqlA");
        bdMySQL.setUsuarioBD("tech_user_mysqlAhora");
        bdMySQL.setPasswordHash("mysql123");

        BaseDatosFranquicia bdOracle = new BaseDatosFranquicia();
        bdOracle.setNombreBD("bd_tech_oracle");
        bdOracle.setTipo(TipoBD.ORACLE);
        bdOracle.setEstado(EstadoBD.NO_CONFIGURADA);
        bdOracle.setUrlConexion("jdbc:oracle:thin:@localhost:1521/XEPDB1");
        bdOracle.setUsuarioBD("tech_user_oracle");
        bdOracle.setPasswordHash("oracle123");
        
        BaseDatosFranquicia bdPG = new BaseDatosFranquicia();
        bdPG.setNombreBD("inventario123");
        bdPG.setTipo(TipoBD.POSTGRESQL);
        bdPG.setEstado(EstadoBD.NO_CONFIGURADA);
        bdPG.setUrlConexion("jdbc:postgresql://localhost:5432/inventario123");
        bdPG.setUsuarioBD("usuario123");
        bdPG.setPasswordHash("pass_pg");
        

        Configuración de BD MongoDB
        BaseDatosFranquicia bdMongo = new BaseDatosFranquicia();
        bdMongo.setNombreBD("inventario_mongo");
        bdMongo.setUsuarioBD("usuario_mongo");
        bdMongo.setPasswordHash("claveSegura123");
        bdMongo.setTipo(TipoBD.MONGODB);
        bdMongo.setEstado(EstadoBD.NO_CONFIGURADA);
         */
        // Asignacion de objetos ne las bd creadas
        // Creacion de Usuarios dentro de una Franquicia
        // Registro
        
        /*
        try {
            registro.registrarUsuarioYNegocio(nuevoUsuario, nuevaFranquicia, Arrays.asList(bdMySQL, bdPG));
            System.out.println("Registro y creacion de bases de datos completado correctamente.");
        } catch (Exception e) {
            System.err.println("Error durante el registro: " + e.getMessage());
            e.printStackTrace();
        }
        */
        
        
        System.out.println("\n========== INICIO DE SESIÓN EN FRANQUICIA ==========");
        String nombreFranquicia = "Tech Café3";
        String usuario = "user1";
        String password = "123";

        GestorAutenticacionFranquicia gestor = new GestorAutenticacionFranquicia();
        boolean autenticado = gestor.autenticar(nombreFranquicia, usuario, password);

        if (autenticado) {
            System.out.println("Usuario autenticado correctamente dentro de la franquicia.");

            // Obtenemos al usuario autenticado desde la sesión
            var sesion = SesionUsuarioFranquiciaController.getInstancia();

            // Mostramos acciones según si es admin o no
            var controlador = new ControladorAccionesUsuario(sesion);
            controlador.mostrarMenuAcciones();

        } else {
            System.out.println(" Usuario o contraseña incorrectos o no registrado en ninguna base de datos de la franquicia.");
        }
        
    }
}