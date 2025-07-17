package com.MiNegocio.configuracioncentral;

import com.MiNegocio.accesousuarios.AutenticadorFranquicia;
import com.MiNegocio.accesousuarios.SesionFranquicia;
import com.MiNegocio.accesousuarios.UsuarioFranquicia;
import com.MiNegocio.configuracioncentral.application.RegistroInicialService;
import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.service.impl.*;
import com.MiNegocio.configuracioncentral.utils.ConstructorTablaInteractiva;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NewMain1 {

    public static void main(String[] args) throws Exception {
        // ==== REPOSITORIOS ====
        UsuarioRepositoryImpl usuarioRepo = new UsuarioRepositoryImpl();
        FranquiciaRepositoryImpl franquiciaRepo = new FranquiciaRepositoryImpl();
        BaseDatosRepositoryImpl bdRepo = new BaseDatosRepositoryImpl();
        ObjetoBDRepositoryImpl objetoRepo = new ObjetoBDRepositoryImpl();

        // ==== SERVICIOS ====
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioRepo);
        FranquiciaServiceImpl franquiciaService = new FranquiciaServiceImpl(franquiciaRepo, usuarioRepo);
        BaseDatosServiceImpl bdService = new BaseDatosServiceImpl(bdRepo, franquiciaRepo);
        ObjetoBDServiceImpl objetoService = new ObjetoBDServiceImpl(
                objetoRepo, bdRepo, usuarioRepo, new ConexionMultiBDFactory(), new GestorObjetosFactory()
        );
        RegistroInicialService registro = new RegistroInicialService(usuarioService, franquiciaService, bdService);

        
        // ==== DATOS DE USUARIO Y FRANQUICIA ====
        Usuario nuevoUsuario = new Usuario("Lucía García", "lucia.garcia@email.com", "ClaveSegura123", LocalDateTime.now());
        Franquicia nuevaFranquicia = new Franquicia("Lucía Bebidas", LocalDateTime.now(), EstadoFranquicia.ACTIVA);

        // Solo se usará una base de datos (MySQL)
        BaseDatosFranquicia bdMySQL = new BaseDatosFranquicia(
                "bd_bebidas_mysql", TipoBD.MYSQL, EstadoBD.NO_CONFIGURADA,
                "jdbc:mysql://localhost:3306/bd_bebidas_mysql",
                "usuario_bebidas", "clavebebidas"
        );

        // ==== REGISTRO INICIAL ====
        try {
            registro.registrarUsuarioYNegocio(nuevoUsuario, nuevaFranquicia, List.of(bdMySQL));
            System.out.println("\nRegistro y base de datos creada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return;
        }

        // ==== CREACIÓN DE UNA TABLA EN LA BD MYSQL ====
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Creación de tabla en MySQL ===");
        ObjetoBDFranquicia tabla = ConstructorTablaInteractiva.crearTablaInteractiva();

        try {
            objetoService.crearObjeto(bdMySQL.getId(), tabla);
            System.out.println("Tabla creada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al crear tabla: " + e.getMessage());
            e.printStackTrace();
        }

        // ==== AUTENTICACIÓN Y CARGA DE DATOS (con GUI) ====
        AutenticadorFranquicia auth = new AutenticadorFranquicia();
        boolean exito = auth.autenticar(nuevaFranquicia.getNombre(), nuevoUsuario.getCorreo(), nuevoUsuario.getPasswordHash());


        if (exito && SesionFranquicia.sesionActiva()) {
            UsuarioFranquicia usuario = SesionFranquicia.obtenerUsuarioActual();
            System.out.println("\nAutenticación exitosa. Es administrador: " + usuario.isEsAdmin());

            

        } else {
            System.out.println("\nFalló la autenticación.");
        }
    }
}
