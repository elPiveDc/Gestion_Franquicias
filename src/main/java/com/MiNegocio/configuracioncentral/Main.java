package com.MiNegocio.configuracioncentral;

import com.MiNegocio.configuracioncentral.application.RegistroInicialService;
import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.service.impl.*;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

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
        nuevoUsuario.setNombre("Luis Gamarra");
        nuevoUsuario.setCorreo("luis@franquicia.com");
        nuevoUsuario.setPasswordHash("admin123");
        nuevoUsuario.setFechaRegistro(LocalDateTime.now());

        // Franquicia
        Franquicia nuevaFranquicia = new Franquicia();
        nuevaFranquicia.setNombre("Tech Café");
        nuevaFranquicia.setFechaCreacion(LocalDateTime.now());
        nuevaFranquicia.setEstado(EstadoFranquicia.ACTIVA);

        /* BD MySQL
        BaseDatosFranquicia bdMySQL = new BaseDatosFranquicia();
        bdMySQL.setNombreBD("bd_tech_mysql");
        bdMySQL.setTipo(TipoBD.MYSQL);
        bdMySQL.setEstado(EstadoBD.NO_CONFIGURADA);
        bdMySQL.setUrlConexion("jdbc:mysql://localhost:3306/bd_tech_mysql");
        bdMySQL.setUsuarioBD("tech_user_mysql");
        bdMySQL.setPasswordHash("mysql123");
         */
 /* BD Oracle
        BaseDatosFranquicia bdOracle = new BaseDatosFranquicia();
        bdOracle.setNombreBD("bd_tech_oracle");
        bdOracle.setTipo(TipoBD.ORACLE);
        bdOracle.setEstado(EstadoBD.NO_CONFIGURADA);
        bdOracle.setUrlConexion("jdbc:oracle:thin:@localhost:1521/XEPDB1");
        bdOracle.setUsuarioBD("tech_user_oracle");
        bdOracle.setPasswordHash("oracle123");
         */
 /* BD postgresql
        BaseDatosFranquicia bdPG = new BaseDatosFranquicia();
        bdPG.setNombreBD("inventario_pg");
        bdPG.setTipo(TipoBD.POSTGRESQL);
        bdPG.setEstado(EstadoBD.NO_CONFIGURADA);
        bdPG.setUrlConexion("jdbc:postgresql://localhost:5432/inventario_pg");
        bdPG.setUsuarioBD("usuario_pg");
        bdPG.setPasswordHash("pass_pg");
         */
        // Configuración de BD MongoDB
        BaseDatosFranquicia bdMongo = new BaseDatosFranquicia();
        bdMongo.setNombreBD("inventario_mongo");
        bdMongo.setUsuarioBD("usuario_mongo");
        bdMongo.setPasswordHash("claveSegura123");
        bdMongo.setTipo(TipoBD.MONGODB);
        bdMongo.setEstado(EstadoBD.NO_CONFIGURADA);
        
        // Registro
        try {
            registro.registrarUsuarioYNegocio(nuevoUsuario, nuevaFranquicia, Arrays.asList(bdMongo));
            System.out.println("Registro y creacion de bases de datos completado correctamente.");
        } catch (Exception e) {
            System.err.println("Error durante el registro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
