package com.MiNegocio.configuracioncentral;

import com.MiNegocio.accesousuarios.AutenticadorFranquicia;
import com.MiNegocio.accesousuarios.SesionFranquicia;
import com.MiNegocio.accesousuarios.UsuarioFranquicia;
import com.MiNegocio.configuracioncentral.application.RegistroInicialService;
import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.factory.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.service.impl.*;

import com.MiNegocio.configuracioncentral.utils.ConstructorTablaInteractiva;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class NewMain {

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

        // ==== USUARIO Y FRANQUICIA DE EJEMPLO ====
        Usuario nuevoUsuario = new Usuario("Jimena Condor", "Jimena@Pajaritos.com", "Pajarito123", LocalDateTime.now());
        Franquicia nuevaFranquicia = new Franquicia("Pajaros bien Ricos", LocalDateTime.now(), EstadoFranquicia.ACTIVA);

        BaseDatosFranquicia bdPG = new BaseDatosFranquicia(
                "bd_pajaros_pg", TipoBD.POSTGRESQL, EstadoBD.NO_CONFIGURADA,
                "jdbc:postgresql://localhost:5432/bd_pajaros_pg",
                "usuario_pajaros_pg", "Pajaros"
        );
        BaseDatosFranquicia bdmysql = new BaseDatosFranquicia(
                "bd_pajaros_mysql", TipoBD.MYSQL, EstadoBD.NO_CONFIGURADA,
                "jdbc:mysql://localhost:3306/bd_pajaros_mysql",
                "usuario_pajaros_mysql", "Pajaros"
        );
        BaseDatosFranquicia bdoracle = new BaseDatosFranquicia(
                "bd_pajaros_oracle", TipoBD.ORACLE, EstadoBD.NO_CONFIGURADA,
                "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                "usuario_pajaros_oracle", "Pajaros"
        );

        try {
            registro.registrarUsuarioYNegocio(nuevoUsuario, nuevaFranquicia, Arrays.asList(bdPG, bdmysql, bdoracle));
            System.out.println("\nRegistro y bases de datos completadas.");
        } catch (Exception e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return;
        }

        // ==== CREACIÓN DE TABLAS INTERACTIVAS ====
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Creación de tabla en " + bdPG.getTipo().name() + " ===");
        ObjetoBDFranquicia tablaPG = ConstructorTablaInteractiva.crearTablaDesdeConsola(scanner);
        System.out.println("\n=== Creación de tabla en " + bdmysql.getTipo().name() + " ===");
        ObjetoBDFranquicia tablamysql = ConstructorTablaInteractiva.crearTablaDesdeConsola(scanner);
        System.out.println("\n=== Creación de tabla en " + bdoracle.getTipo().name() + " ===");
        ObjetoBDFranquicia tablaoracle = ConstructorTablaInteractiva.crearTablaDesdeConsola(scanner);

        try {
            objetoService.crearObjeto(bdPG.getId(), tablaPG);
            objetoService.crearObjeto(bdmysql.getId(), tablamysql);
            objetoService.crearObjeto(bdoracle.getId(), tablaoracle);
            System.out.println("\nTodas las tablas se crearon exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
            e.printStackTrace();
        }

        // ==== AUTENTICACIÓN Y CARGA DE DATOS ====
        AutenticadorFranquicia auth = new AutenticadorFranquicia();
        boolean exito = auth.autenticar(nuevaFranquicia.getNombre(), nuevoUsuario.getCorreo(), nuevoUsuario.getPasswordHash());

        if (exito && SesionFranquicia.sesionActiva()) {
            UsuarioFranquicia usuario = SesionFranquicia.obtenerUsuarioActual();
            System.out.println("\nAutenticación exitosa. Es administrador: " + usuario.isEsAdmin());
            mostrarModuloIngresoDatos(objetoRepo, bdRepo, bdmysql.getId(), bdmysql.getNombreBD() ,scanner);
            mostrarModuloIngresoDatos(objetoRepo, bdRepo, bdPG.getId(), bdPG.getNombreBD(),scanner);
            mostrarModuloIngresoDatos(objetoRepo, bdRepo, bdoracle.getId(),bdoracle.getNombreBD() ,scanner);
        } else {
            System.out.println("\nFalló la autenticación.");
        }
    }

    private static void mostrarModuloIngresoDatos(
            ObjetoBDRepositoryImpl objetoRepo,
            BaseDatosRepositoryImpl bdRepo,
            int idBD,
            String bdnombre,
            Scanner scanner
    ) {
        ServicioCargaDatosImpl servicioCarga = new ServicioCargaDatosImpl(objetoRepo, bdRepo);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Tablas disponibles para la franquicia en la BD "+ bdnombre +" ===");
            var tablas = objetoRepo.listarObjetosPorBD(idBD);

            if (tablas.isEmpty()) {
                System.out.println("No hay tablas disponibles aún.");
                break;
            }

            for (int i = 0; i < tablas.size(); i++) {
                ObjetoBDFranquicia t = tablas.get(i);
                System.out.printf("%d. %s (%s)\n", i + 1, t.getNombreTabla(), t.getTipoObjeto());
            }

            System.out.print("Selecciona una tabla para ingresar datos (0 para salir): ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion == 0) {
                continuar = false;
                break;
            }

            if (opcion < 1 || opcion > tablas.size()) {
                System.out.println("Opción inválida.");
                continue;
            }

            ObjetoBDFranquicia seleccionada = tablas.get(opcion - 1);

            System.out.println("\nElige el método de carga:");
            System.out.println("1. Manual");
            System.out.println("2. Desde archivo JSON");
            System.out.println("3. Desde archivo CSV");
            System.out.print("Opción: ");
            int metodo = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            try {
                switch (metodo) {
                    case 1:
                        servicioCarga.cargarDesdeManual(seleccionada.getIdObjeto());
                        break;
                    case 2:
                        servicioCarga.cargarDesdeJSON(seleccionada.getIdObjeto());
                        break;
                    case 3:
                        servicioCarga.cargarDesdeCSV(seleccionada.getIdObjeto());
                        break;
                    default:
                        System.out.println("Método no válido.");
                }
            } catch (Exception e) {
                System.err.println("Error durante carga de datos: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("\nFin del ingreso de datos.");
    }

}
