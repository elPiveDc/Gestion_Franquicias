package com.MiNegocio.consultas;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ConsultaEstructurada {

    public void consultar(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto, Scanner scanner) throws Exception {
        System.out.println("\n--- Opciones de consulta para la tabla '" + objeto.getNombreTabla() + "' ---");
        System.out.println("1. Ver todos los datos");
        System.out.println("2. Filtrar por una columna");
        System.out.println("3. Hacer JOIN con otra tabla");
        System.out.print("Seleccione una opción: ");
        int tipoConsulta = scanner.nextInt();
        scanner.nextLine();

        if (bd.getTipo().toString().equals("POSTGRESQL")) {
            
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {
                switch (tipoConsulta) {
                    case 1 ->
                        ejecutarConsultaSimple(conn, objeto, bd);
                    case 2 ->
                        ejecutarConsultaFiltrada(conn, objeto, bd, scanner);
                    case 3 ->
                        System.out.println("JOIN aún no implementado como módulo independiente.");
                    default ->
                        System.out.println("Opción inválida.");
                }
            }

        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd)) {
                switch (tipoConsulta) {
                    case 1 ->
                        ejecutarConsultaSimple(conn, objeto, bd);
                    case 2 ->
                        ejecutarConsultaFiltrada(conn, objeto, bd, scanner);
                    case 3 ->
                        System.out.println("JOIN aún no implementado como módulo independiente.");
                    default ->
                        System.out.println("Opción inválida.");
                }
            }
        }
    }

    private void ejecutarConsultaSimple(Connection conn, ObjetoBDFranquicia objeto, BaseDatosFranquicia bd) throws Exception {
        String sql = SQLHelper.generarConsultaSimple(objeto.getNombreTabla(), bd.getTipo());
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            mostrarResultados(rs);
        }
    }

    private void ejecutarConsultaFiltrada(Connection conn, ObjetoBDFranquicia objeto, BaseDatosFranquicia bd, Scanner scanner) throws Exception {
        JsonNode columnas = new ObjectMapper().readTree(objeto.getColumnas());

        System.out.println("\n--- Columnas disponibles ---");
        for (int i = 0; i < columnas.size(); i++) {
            System.out.println((i + 1) + ". " + columnas.get(i).get("nombre").asText());
        }

        System.out.print("Seleccione una columna para filtrar: ");
        int colSeleccionada = scanner.nextInt();
        scanner.nextLine();

        if (colSeleccionada < 1 || colSeleccionada > columnas.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        String nombreColumna = columnas.get(colSeleccionada - 1).get("nombre").asText();
        System.out.print("Ingrese el valor para '" + nombreColumna + "': ");
        String valor = scanner.nextLine();

        String sql = SQLHelper.generarConsultaFiltrada(objeto.getNombreTabla(), List.of(nombreColumna), bd.getTipo());

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);
            ResultSet rs = stmt.executeQuery();
            mostrarResultados(rs);
        }
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
