package com.MiNegocio.accesousuarios.control;

import java.util.Scanner;

public class ControladorAccionesUsuario {

    private final SesionUsuarioFranquiciaController sesion;

    public ControladorAccionesUsuario(SesionUsuarioFranquiciaController sesion) {
        this.sesion = sesion;
    }

    public void mostrarMenuAcciones() {
        System.out.println("===================================");
        System.out.println("Usuario autenticado: " + sesion.getConexionFranquicia().getNombreUsuario());
        System.out.println("Tipo de BD: " + sesion.getConexionFranquicia().getTipoBD());
        System.out.println(" Rol: " + (sesion.getConexionFranquicia().isAdmin() ? "ADMINISTRADOR" : "EMPLEADO"));
        System.out.println("===================================");

        if (sesion.getConexionFranquicia().isAdmin()) {
            mostrarMenuAdministrador();
        } else {
            mostrarMenuEmpleado();
        }
    }

    private void mostrarMenuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Crear nueva base de datos");
            System.out.println("2. Crear objetos en una BD (tabla, vista, función)");
            System.out.println("3. Consultar datos de la franquicia");
            System.out.println("4. Ver usuarios de la franquicia");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Redirigiendo a creación de base de datos...");
                    // Llamar al módulo correspondiente
                    break;
                case 2:
                    System.out.println("Redirigiendo a creación de objetos...");
                    // Llamar al módulo correspondiente
                    break;
                case 3:
                    System.out.println("Mostrando panel de consultas...");
                    // Llamar al módulo de consultas
                    break;
                case 4:
                    System.out.println("Mostrando usuarios de la franquicia...");
                    // Llamar al módulo de usuarios
                    break;
                case 0:
                    System.out.println("Saliendo del menú.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenuEmpleado() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ EMPLEADO ---");
            System.out.println("1. Realizar consulta");
            System.out.println("2. Ver mi información");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Redirigiendo al módulo de consultas...");
                    // Llamar al módulo correspondiente
                    break;
                case 2:
                    System.out.println("Mostrando información personal...");
                    // Mostrar detalles personales
                    break;
                case 0:
                    System.out.println("Saliendo del menú.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }
}
