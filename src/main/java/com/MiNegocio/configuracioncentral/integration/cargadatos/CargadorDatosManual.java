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
import java.util.List;
import java.util.Scanner;

public class CargadorDatosManual implements CargadorDatos {

    @Override
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object columnasData) throws Exception {

        JsonNode columnasArray;

        // Determinar si el origen es String o List
        if (columnasData instanceof String) {
            ObjectMapper mapper = new ObjectMapper();
            columnasArray = mapper.readTree((String) columnasData);
        } else if (columnasData instanceof List) {
            ObjectMapper mapper = new ObjectMapper();
            columnasArray = mapper.valueToTree(columnasData);
        } else {
            throw new IllegalArgumentException("Formato de columnas no reconocido");
        }

        Scanner scanner = new Scanner(System.in);

        if (bd.getTipo().toString().equals("POSTGRESQL")) {

            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {
                boolean continuar = true;

                while (continuar) {
                    System.out.print("\n¿Deseas insertar una nueva fila? (s/n): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();
                    if (!respuesta.equals("s")) {
                        continuar = false;
                        break;
                    }

                    String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        System.out.println("\n--- Ingreso de datos manual para tabla '" + nombreTabla + "' ---");

                        for (int i = 0; i < columnasArray.size(); i++) {
                            JsonNode columna = columnasArray.get(i);
                            String nombre = columna.get("nombre").asText();
                            String tipo = columna.get("tipo").asText();

                            System.out.print("Ingrese valor para '" + nombre + "': ");
                            String valor = scanner.nextLine();

                            if (valor.trim().isEmpty()) {
                                stmt.setNull(i + 1, Types.NULL);
                                continue;
                            }

                            switch (tipo.toLowerCase()) {
                                case "entero":
                                    stmt.setInt(i + 1, Integer.parseInt(valor));
                                    break;
                                case "decimal":
                                    stmt.setDouble(i + 1, Double.parseDouble(valor));
                                    break;
                                case "fecha":
                                    stmt.setDate(i + 1, java.sql.Date.valueOf(valor)); // YYYY-MM-DD
                                    break;
                                case "cadena":
                                default:
                                    stmt.setString(i + 1, valor);
                                    break;
                            }
                        }

                        stmt.executeUpdate();
                        System.out.println("Fila insertada correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error durante carga de datos: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd)) {
                boolean continuar = true;

                while (continuar) {
                    System.out.print("\n¿Deseas insertar una nueva fila? (s/n): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();
                    if (!respuesta.equals("s")) {
                        continuar = false;
                        break;
                    }

                    String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        System.out.println("\n--- Ingreso de datos manual para tabla '" + nombreTabla + "' ---");

                        for (int i = 0; i < columnasArray.size(); i++) {
                            JsonNode columna = columnasArray.get(i);
                            String nombre = columna.get("nombre").asText();
                            String tipo = columna.get("tipo").asText();

                            System.out.print("Ingrese valor para '" + nombre + "': ");
                            String valor = scanner.nextLine();

                            if (valor.trim().isEmpty()) {
                                stmt.setNull(i + 1, Types.NULL);
                                continue;
                            }

                            switch (tipo.toLowerCase()) {
                                case "entero":
                                    stmt.setInt(i + 1, Integer.parseInt(valor));
                                    break;
                                case "decimal":
                                    stmt.setDouble(i + 1, Double.parseDouble(valor));
                                    break;
                                case "fecha":
                                    stmt.setDate(i + 1, java.sql.Date.valueOf(valor)); // YYYY-MM-DD
                                    break;
                                case "cadena":
                                default:
                                    stmt.setString(i + 1, valor);
                                    break;
                            }
                        }

                        stmt.executeUpdate();
                        System.out.println("Fila insertada correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error durante carga de datos: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
