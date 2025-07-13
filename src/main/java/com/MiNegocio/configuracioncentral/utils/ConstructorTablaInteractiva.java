package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class ConstructorTablaInteractiva {

    public static ObjetoBDFranquicia crearTablaDesdeConsola(Scanner scanner) throws Exception {
        ObjetoBDFranquicia tabla = new ObjetoBDFranquicia();
        List<Map<String, String>> columnas = new ArrayList<>();

        System.out.println("=== CREACIÓN DE TABLA ===");
        System.out.print("Nombre de la tabla: ");
        String nombreTabla = scanner.nextLine();
        tabla.setNombreTabla(nombreTabla);
        tabla.setTipoObjeto("TABLA");
        tabla.setEsTablaUsuarios(false); // Se puede personalizar según lógica

        boolean agregarOtra = true;
        while (agregarOtra) {
            Map<String, String> columna = new HashMap<>();

            System.out.print("Nombre de la columna: ");
            columna.put("nombre", scanner.nextLine());

            System.out.print("Tipo (entero, cadena, decimal, fecha): ");
            columna.put("tipo", scanner.nextLine());

            System.out.print("Restricciones (ej: PRIMARY KEY, NOT NULL), dejar vacío si ninguna: ");
            columna.put("restricciones", scanner.nextLine());

            columnas.add(columna);

            System.out.print("¿Desea agregar otra columna? (s/n): ");
            String resp = scanner.nextLine().toLowerCase();
            agregarOtra = resp.equals("s");
        }

        // Convertir a JSON usando Jackson
        ObjectMapper mapper = new ObjectMapper();
        String columnasJson = mapper.writeValueAsString(columnas);
        tabla.setColumnas(columnasJson);
        tabla.setFechaCreacion(new Date());

        return tabla;
    }
}
