package com.MiNegocio.consultas;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.service.IAService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ConsultaIA {

    public void consultar(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto, Scanner scanner) throws Exception {
        
        System.out.println("\n--- Consulta IA para tabla '" + objeto.getNombreTabla() + "' ---");
        System.out.println("Describe lo que deseas consultar en lenguaje natural:");
        String pregunta = scanner.nextLine();

        String sql = generarSQLDesdePregunta(pregunta, objeto, bd);

        if (sql == null || sql.isBlank()) {
            System.out.println("No se pudo generar una consulta válida.");
            return;
        }

        System.out.println("Consulta generada: " + sql);

        if (bd.getTipo().toString().equals("POSTGRESQL")) {
            
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL",bd.getUrlConexion()); 
                Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                mostrarResultados(rs);
            } catch (Exception e) {
                System.out.println("Error durante la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd); 
                Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                mostrarResultados(rs);
            } catch (Exception e) {
                System.out.println("Error durante la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private String generarSQLDesdePregunta(String texto, ObjetoBDFranquicia objeto, BaseDatosFranquicia bd) throws Exception {
        
        StringBuilder esquema = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode columnas = mapper.readTree(objeto.getColumnas());

        esquema.append("Tabla: ").append(objeto.getNombreTabla()).append("\n");
        esquema.append("Columnas:\n");
        for (JsonNode col : columnas) {
            esquema.append("- ").append(col.get("nombre").asText())
                    .append(" (").append(col.get("tipo").asText()).append(")\n");
        }

        String tipoBD = switch (bd.getTipo().name()) {
            case "POSTGRESQL" ->
                "PostgreSQL (usa LIMIT, tipos como SERIAL, TEXT)";
            case "MYSQL" ->
                "MySQL (usa LIMIT, tipos como INT, VARCHAR)";
            case "ORACLE" ->
                "Oracle SQL (usa ROWNUM, tipos como VARCHAR2, NUMBER)";
            default ->
                bd.getTipo().name();
        };

        String prompt = """
        Eres un experto en SQL. Genera una consulta SQL válida y funcional para el siguiente requerimiento del usuario.
        Solo responde con la consulta SQL, sin explicaciones.

        Tipo de base de datos: %s
        %s
        Petición del usuario: "%s"

        SQL:
        """.formatted(tipoBD, esquema, texto);

        String resultado = IAService.generarSQLDesdePregunta(prompt).trim();

        if (bd.getTipo().name().equals("ORACLE")) {
            resultado = resultado.replace("!=", "<>");
        }
        if (resultado.contains("\n")) {
            resultado = resultado.lines()
                    .filter(line -> line.trim().toUpperCase().startsWith("SELECT")
                    || line.trim().toUpperCase().startsWith("WITH")
                    || line.trim().toUpperCase().startsWith("DELETE")
                    || line.trim().toUpperCase().startsWith("UPDATE"))
                    .findFirst()
                    .orElse(resultado);
        }

        return resultado;
    }

    private void mostrarResultados(ResultSet rs) throws Exception {
        int columnas = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnas; i++) {
                System.out.print(rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i) + " | ");
            }
            System.out.println();
        }
    }
}
