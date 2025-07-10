package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class SQLHelper {

    public static String generarCreateSQL(ObjetoBDFranquicia objeto, String tipoBD, String nombreBD) throws Exception {
        switch (objeto.getTipoObjeto().toUpperCase()) {
            case "TABLA":
                return generarCreateTableSQL(objeto, tipoBD, nombreBD);
            default:
                throw new UnsupportedOperationException("Tipo no soportado: " + objeto.getTipoObjeto());
        }
    }

    public static String generarCreateTableSQL(ObjetoBDFranquicia objeto, String tipoBD, String nombreBD) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> columnas = mapper.readValue(objeto.getColumnas(), List.class);

        boolean usarComillasDobles = tipoBD.equalsIgnoreCase("postgresql");
        String abrir = usarComillasDobles ? "\"" : "";
        String cerrar = usarComillasDobles ? "\"" : "";

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");

        // Especificar base de datos si aplica
        if ("mysql".equalsIgnoreCase(tipoBD) && nombreBD != null && !nombreBD.isBlank()) {
            sb.append(nombreBD).append(".");
        }

        sb.append(abrir).append(objeto.getNombreTabla()).append(cerrar).append(" (\n");

        for (int i = 0; i < columnas.size(); i++) {
            Map<String, String> col = columnas.get(i);
            String nombreColumna = col.get("nombre");
            String tipoColumna = mapearTipo(col.get("tipo"), tipoBD);
            String restricciones = col.getOrDefault("restricciones", "");

            sb.append("  ")
                    .append(abrir).append(nombreColumna).append(cerrar).append(" ")
                    .append(tipoColumna);

            if (!restricciones.isEmpty()) {
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

    private static String mapearTipo(String tipo, String bd) {
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
}
