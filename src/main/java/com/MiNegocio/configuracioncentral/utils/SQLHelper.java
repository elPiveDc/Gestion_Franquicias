package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.domain.TipoBD;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SQLHelper {

    public static String generarCreateSQL(ObjetoBDFranquicia objeto, String tipoBD, String nombreBD) throws Exception {
        return switch (tipoBD.toLowerCase()) {
            case "mysql" ->
                generarCreateTableMySQL(objeto, nombreBD);
            case "postgresql" ->
                generarCreateTablePostgreSQL(objeto);
            case "oracle" ->
                generarCreateTableOracle(objeto);
            default ->
                throw new UnsupportedOperationException("Tipo de base de datos no soportado: " + tipoBD);
        };
    }

    // ---------- MYSQL ----------
    private static String generarCreateTableMySQL(ObjetoBDFranquicia objeto, String nombreBD) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> columnas = mapper.readValue(objeto.getColumnas(), List.class);

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        if (nombreBD != null && !nombreBD.isBlank()) {
            sb.append(nombreBD).append(".");
        }
        sb.append(objeto.getNombreTabla()).append(" (\n");

        for (int i = 0; i < columnas.size(); i++) {
            Map<String, String> col = columnas.get(i);
            String nombre = col.get("nombre");
            String tipo = mapearTipo("mysql", col.get("tipo"));
            String restricciones = col.getOrDefault("restricciones", "");

            sb.append("  ").append(nombre).append(" ").append(tipo);
            if (!restricciones.isBlank()) {
                sb.append(" ").append(restricciones);
            }
            if (i < columnas.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append(");");
        return sb.toString();
    }

    // ---------- POSTGRESQL ----------
    private static String generarCreateTablePostgreSQL(ObjetoBDFranquicia objeto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> columnas = mapper.readValue(objeto.getColumnas(), List.class);

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append("\"").append(objeto.getNombreTabla()).append("\" (\n");

        for (int i = 0; i < columnas.size(); i++) {
            Map<String, String> col = columnas.get(i);
            String nombre = col.get("nombre");
            String tipo = mapearTipo("postgresql", col.get("tipo"));
            String restricciones = col.getOrDefault("restricciones", "");

            sb.append("  ").append("\"").append(nombre).append("\" ").append(tipo);
            if (!restricciones.isBlank()) {
                sb.append(" ").append(restricciones);
            }
            if (i < columnas.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append(");");
        return sb.toString();
    }

    // ---------- ORACLE ----------
    private static String generarCreateTableOracle(ObjetoBDFranquicia objeto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> columnas = mapper.readValue(objeto.getColumnas(), List.class);

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(objeto.getNombreTabla()).append(" (\n");

        for (int i = 0; i < columnas.size(); i++) {
            Map<String, String> col = columnas.get(i);
            String nombre = col.get("nombre");
            String tipo = mapearTipo("oracle", col.get("tipo"));
            String restricciones = col.getOrDefault("restricciones", "");

            sb.append("  ").append(nombre).append(" ").append(tipo);
            if (!restricciones.isBlank()) {
                sb.append(" ").append(restricciones);
            }
            if (i < columnas.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append(")");
        return sb.toString();
    }

    // ---------- MAPEO DE TIPOS ----------
    private static String mapearTipo(String bd, String tipo) {
        return switch (bd.toLowerCase()) {
            case "mysql" ->
                switch (tipo.toLowerCase()) {
                    case "entero" ->
                        "INT";
                    case "cadena" ->
                        "VARCHAR(255)";
                    case "fecha" ->
                        "DATE";
                    default ->
                        tipo.toUpperCase();
                };
            case "postgresql" ->
                switch (tipo.toLowerCase()) {
                    case "entero" ->
                        "INTEGER";
                    case "cadena" ->
                        "VARCHAR(255)";
                    case "fecha" ->
                        "DATE";
                    default ->
                        tipo.toUpperCase();
                };
            case "oracle" ->
                switch (tipo.toLowerCase()) {
                    case "entero" ->
                        "NUMBER";
                    case "cadena" ->
                        "VARCHAR2(255)";
                    case "fecha" ->
                        "DATE";
                    default ->
                        tipo.toUpperCase();
                };
            default ->
                throw new IllegalArgumentException("Base de datos no soportada: " + bd);
        };
    }

    public static String extraerColumnasDeCreateSQL(String createSQL) throws Exception {
        List<Map<String, String>> columnas = new ArrayList<>();

        // Normalizar espacios y saltos de línea
        createSQL = createSQL.replaceAll("[\\n\\r]+", " ").trim();

        // Extraer contenido entre paréntesis (las columnas)
        int start = createSQL.indexOf('(');
        int end = createSQL.lastIndexOf(')');
        if (start == -1 || end == -1 || start >= end) {
            throw new IllegalArgumentException("CREATE TABLE inválido: no se encontraron paréntesis");
        }

        String columnasStr = createSQL.substring(start + 1, end).trim();

        // Separar columnas por coma, respetando posibles comas internas (ej. ENUM o CHECK)
        List<String> definiciones = splitColumnDefinitions(columnasStr);

        for (String def : definiciones) {
            def = def.trim();
            if (def.isBlank()) {
                continue;
            }

            String[] partes = def.split("\\s+", 3); // nombre tipo restricciones

            if (partes.length < 2) {
                continue;
            }

            Map<String, String> columna = new LinkedHashMap<>();
            columna.put("nombre", partes[0].replaceAll("\"", "")); // quitar comillas
            columna.put("tipo", mapearTipoAJson(partes[1]));
            columna.put("restricciones", partes.length >= 3 ? partes[2].trim() : "");

            columnas.add(columna);
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(columnas);
    }

    private static List<String> splitColumnDefinitions(String input) {
        List<String> result = new ArrayList<>();
        int parenLevel = 0;
        StringBuilder current = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '(') {
                parenLevel++;
            }
            if (c == ')') {
                parenLevel--;
            }
            if (c == ',' && parenLevel == 0) {
                result.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            result.add(current.toString().trim());
        }
        return result;
    }

    private static String mapearTipoAJson(String tipoSQL) {
        tipoSQL = tipoSQL.toLowerCase();
        if (tipoSQL.startsWith("varchar") || tipoSQL.startsWith("text") || tipoSQL.startsWith("char")) {
            return "cadena";
        }
        if (tipoSQL.startsWith("int") || tipoSQL.startsWith("integer") || tipoSQL.startsWith("number")) {
            return "entero";
        }
        if (tipoSQL.startsWith("date") || tipoSQL.startsWith("timestamp")) {
            return "fecha";
        }
        return tipoSQL; // por si acaso
    }

    //metodo para insert manual
    public static String generarInsertSQL(String nombreTabla, String columnasJson, TipoBD tipoBD) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode columnasArray = mapper.readTree(columnasJson);

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ").append(nombreTabla).append(" (");

            for (int i = 0; i < columnasArray.size(); i++) {
                JsonNode columna = columnasArray.get(i);
                sb.append(columna.get("nombre").asText());
                if (i < columnasArray.size() - 1) {
                    sb.append(", ");
                }
            }

            sb.append(") VALUES (");

            for (int i = 0; i < columnasArray.size(); i++) {
                sb.append("?");
                if (i < columnasArray.size() - 1) {
                    sb.append(", ");
                }
            }

            sb.append(")");

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar SQL de inserción: " + e.getMessage(), e);
        }
    }

    public static String generarConsultaSimple(String nombreTabla, TipoBD tipoBD) {
        return "SELECT * FROM " + formatearNombreTabla(nombreTabla, tipoBD);
    }

    //consultas    
    public static String generarConsultaFiltrada(String nombreTabla, List<String> columnasFiltro, TipoBD tipoBD) {
        String tablaFormateada = formatearNombreTabla(nombreTabla, tipoBD);
        String condiciones = columnasFiltro.stream()
                .map(col -> col + " = ?")
                .collect(Collectors.joining(" AND "));
        return "SELECT * FROM " + tablaFormateada + " WHERE " + condiciones;
    }

    public static String generarConsultaJoin(String tabla1, String tabla2, String columna1, String columna2, TipoBD tipoBD) {
        return "SELECT * FROM " + formatearNombreTabla(tabla1, tipoBD)
                + " t1 INNER JOIN " + formatearNombreTabla(tabla2, tipoBD) + " t2 ON t1." + columna1 + " = t2." + columna2;
    }

    private static String formatearNombreTabla(String nombre, TipoBD tipoBD) {
        String tipo = null;
        switch (tipoBD) {
            case POSTGRESQL ->
                tipo = "\"" + nombre + "\"";
            case ORACLE ->
                tipo = nombre.toUpperCase();
            case MYSQL ->
                tipo = "`" + nombre + "`";
        }
        return tipo;
    }
}
