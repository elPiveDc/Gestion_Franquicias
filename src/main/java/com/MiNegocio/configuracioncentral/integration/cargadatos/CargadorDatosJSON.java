package com.MiNegocio.configuracioncentral.integration.cargadatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

public class CargadorDatosJSON implements CargadorDatos {

    @Override
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object columnasData) throws Exception {
        // Paso 1: Parsear definición de columnas desde el tercer parámetro (String o List)
        JsonNode columnasArray;
        ObjectMapper mapper = new ObjectMapper();

        if (columnasData instanceof String) {
            columnasArray = mapper.readTree((String) columnasData);
        } else if (columnasData instanceof List) {
            columnasArray = mapper.valueToTree(columnasData);
        } else {
            throw new IllegalArgumentException("El parámetro columnasData debe ser un JSON válido o una lista de columnas.");
        }

        // Paso 2: Solicitar la ruta al archivo JSON con datos
        System.out.print("Ingrese la ruta del archivo JSON con los datos: ");
        String ruta = new java.util.Scanner(System.in).nextLine();
        File archivoJSON = new File(ruta);

        if (!archivoJSON.exists()) {
            throw new IllegalArgumentException("El archivo JSON no existe: " + ruta);
        }

        JsonNode filas = mapper.readTree(archivoJSON);
        if (!filas.isArray()) {
            throw new IllegalArgumentException("El archivo debe contener un array JSON de objetos.");
        }

        // Paso 3: Preparar conexión y statement
        if (bd.getTipo().toString().equals("POSTGRESQL")) {
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {
                String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                for (JsonNode fila : filas) {
                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        int i = 1;
                        for (JsonNode columna : columnasArray) {
                            String nombre = columna.get("nombre").asText();
                            String tipo = columna.get("tipo").asText();

                            JsonNode valor = fila.get(nombre);
                            if (valor == null || valor.isNull()) {
                                stmt.setNull(i, Types.NULL);
                            } else {
                                switch (tipo.toLowerCase()) {
                                    case "entero" ->
                                        stmt.setInt(i, valor.asInt());
                                    case "decimal" ->
                                        stmt.setDouble(i, valor.asDouble());
                                    case "fecha" ->
                                        stmt.setDate(i, java.sql.Date.valueOf(valor.asText())); // formato: YYYY-MM-DD
                                    case "cadena" ->
                                        stmt.setString(i, valor.asText());
                                    default ->
                                        stmt.setString(i, valor.asText()); // Fallback
                                }
                            }
                            i++;
                        }

                        stmt.executeUpdate();
                        System.out.println("Fila insertada correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error al insertar fila: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd)) {
                String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                for (JsonNode fila : filas) {
                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        int i = 1;
                        for (JsonNode columna : columnasArray) {
                            String nombre = columna.get("nombre").asText();
                            String tipo = columna.get("tipo").asText();

                            JsonNode valor = fila.get(nombre);
                            if (valor == null || valor.isNull()) {
                                stmt.setNull(i, Types.NULL);
                            } else {
                                switch (tipo.toLowerCase()) {
                                    case "entero" ->
                                        stmt.setInt(i, valor.asInt());
                                    case "decimal" ->
                                        stmt.setDouble(i, valor.asDouble());
                                    case "fecha" ->
                                        stmt.setDate(i, java.sql.Date.valueOf(valor.asText())); // formato: YYYY-MM-DD
                                    case "cadena" ->
                                        stmt.setString(i, valor.asText());
                                    default ->
                                        stmt.setString(i, valor.asText()); // Fallback
                                }
                            }
                            i++;
                        }

                        stmt.executeUpdate();
                        System.out.println("Fila insertada correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error al insertar fila: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        
        
    }
}
