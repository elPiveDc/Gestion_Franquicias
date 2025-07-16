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
import com.MiNegocio.consultas.ConsultaEstructurada;
import com.MiNegocio.consultas.ConsultaIA;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NewMainFinal {

    public static void main(String[] args) throws Exception {

        UsuarioRepositoryImpl usuarioRepo = new UsuarioRepositoryImpl();
        FranquiciaRepositoryImpl franquiciaRepo = new FranquiciaRepositoryImpl();
        BaseDatosRepositoryImpl bdRepo = new BaseDatosRepositoryImpl();
        ObjetoBDRepositoryImpl objetoRepo = new ObjetoBDRepositoryImpl();

        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioRepo);
        FranquiciaServiceImpl franquiciaService = new FranquiciaServiceImpl(franquiciaRepo, usuarioRepo);
        BaseDatosServiceImpl bdService = new BaseDatosServiceImpl(bdRepo, franquiciaRepo);
        ObjetoBDServiceImpl objetoService = new ObjetoBDServiceImpl(
                objetoRepo, bdRepo, usuarioRepo, new ConexionMultiBDFactory(), new GestorObjetosFactory()
        );
        RegistroInicialService registro = new RegistroInicialService(usuarioService, franquiciaService, bdService);

        Usuario nuevoUsuario
                = new Usuario("Jimena Condor", "Jimena@Carros.com", "Carros123", LocalDateTime.now());

        Franquicia nuevaFranquicia
                = new Franquicia("Pajaros bien Ricos", LocalDateTime.now(), EstadoFranquicia.ACTIVA);

        
        BaseDatosFranquicia bdPG = new BaseDatosFranquicia(
                "bd_pajaros_pg", TipoBD.POSTGRESQL, EstadoBD.NO_CONFIGURADA,
                "jdbc:postgresql://localhost:5432/bd_pajaros_pg",
                "usuario_pajaros_pg", "Pajaros"
        );
        
        BaseDatosFranquicia bdmysql = new BaseDatosFranquicia(
                "bd_carros_mysql", TipoBD.MYSQL, EstadoBD.NO_CONFIGURADA,
                "jdbc:mysql://localhost:3306/bd_carros_mysql",
                "usuario_carros_mysql", "Carros"
        );

        
        BaseDatosFranquicia bdoracle = new BaseDatosFranquicia(
                "bd_pajaros_oracle", TipoBD.ORACLE, EstadoBD.NO_CONFIGURADA,
                "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                "usuario_pajaros_oracle", "Pajaros"
        );
        
        try {
            registro.registrarUsuarioYNegocio(nuevoUsuario, nuevaFranquicia, Arrays.asList(bdPG,bdoracle, bdmysql));
            System.out.println("\nRegistro y bases de datos completadas.");
        } catch (Exception e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        /*
        System.out.println("\n=== Creación de tabla en " + bdPG.getTipo().name() + " ===");
        ObjetoBDFranquicia tablaPG = ConstructorTablaInteractiva.crearTablaDesdeConsola(scanner);
        */
        
        System.out.println("\n=== Creación de tabla en " + bdmysql.getTipo().name() + " ===");
        ObjetoBDFranquicia tablamysql = ConstructorTablaInteractiva.crearTablaInteractiva();
        
        /*
        System.out.println("\n=== Creación de tabla en " + bdoracle.getTipo().name() + " ===");
        ObjetoBDFranquicia tablaoracle = ConstructorTablaInteractiva.crearTablaDesdeConsola(scanner);
        */

        try {
            objetoService.crearObjeto(bdmysql.getId(), tablamysql);
            System.out.println("\nTodas las tablas se crearon exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
            e.printStackTrace();
        }

        AutenticadorFranquicia auth = new AutenticadorFranquicia();
        boolean exito = auth.autenticar(nuevaFranquicia.getNombre(), nuevoUsuario.getCorreo(), nuevoUsuario.getPasswordHash());

        if (exito && SesionFranquicia.sesionActiva()) {
            
            
            UsuarioFranquicia usuario = SesionFranquicia.obtenerUsuarioActual();
            System.out.println("\nAutenticación exitosa. Es administrador: " + usuario.isEsAdmin());

            mostrarModuloIngresoDatos(objetoRepo, bdRepo, bdmysql.getId(), bdmysql.getNombreBD(), scanner);

            mostrarModuloConsultas(objetoRepo, bdRepo, bdmysql.getId(), bdmysql.getNombreBD(), scanner);

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
            System.out.println("\n=== Tablas disponibles para la franquicia en la BD " + bdnombre + " ===");
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
            scanner.nextLine();

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
            scanner.nextLine();

            try {
                switch (metodo) {
                    case 1 ->
                        servicioCarga.cargarDesdeManual(seleccionada.getIdObjeto());
                    case 2 ->
                        servicioCarga.cargarDesdeJSON(seleccionada.getIdObjeto());
                        
                    case 3 ->
                        servicioCarga.cargarDesdeCSV(seleccionada.getIdObjeto());
                        
                    default ->
                        System.out.println("Método no válido.");
                }
            } catch (Exception e) {
                System.err.println("Error durante carga de datos: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("\nFin del ingreso de datos.");
    }

    private static void mostrarModuloConsultas(
            ObjetoBDRepositoryImpl objetoRepo,
            BaseDatosRepositoryImpl bdRepo,
            int idBD,
            String bdNombre,
            Scanner scanner
    ) {
        ConsultaEstructurada consultaEstructurada = new ConsultaEstructurada();
        ConsultaIA consultaIA = new ConsultaIA(); // ya te paso esta nueva clase adaptada abajo
        var tablas = objetoRepo.listarObjetosPorBD(idBD);
        var bd = bdRepo.buscarPorId(idBD);

        if (tablas.isEmpty()) {
            System.out.println("No hay tablas disponibles para consultas en " + bdNombre);
            return;
        }

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Tablas disponibles en la BD " + bdNombre + " ===");
            for (int i = 0; i < tablas.size(); i++) {
                ObjetoBDFranquicia t = tablas.get(i);
                System.out.printf("%d. %s (%s)\n", i + 1, t.getNombreTabla(), t.getTipoObjeto());
            }

            System.out.print("Seleccione una tabla para consultar (0 para salir): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 0) {
                continuar = false;
                break;
            }

            if (opcion < 1 || opcion > tablas.size()) {
                System.out.println("Opción inválida.");
                continue;
            }

            ObjetoBDFranquicia seleccionada = tablas.get(opcion - 1);

            System.out.println("\nTipo de consulta:");
            System.out.println("1. Consulta estructurada");
            System.out.println("2. Consulta por lenguaje natural (IA)");
            System.out.print("Opción: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (tipo) {
                    case 1 ->
                        consultaEstructurada.consultar(bd, seleccionada, scanner);
                    case 2 ->
                        consultaIA.consultar(bd, seleccionada, scanner);
                    default ->
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.err.println("Error durante la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("\nFin del módulo de consultas.");
    }

}
