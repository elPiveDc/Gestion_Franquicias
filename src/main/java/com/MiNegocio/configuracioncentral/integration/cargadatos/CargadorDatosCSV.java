package com.MiNegocio.configuracioncentral.integration.cargadatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;

public class CargadorDatosCSV implements CargadorDatos {

    @Override
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object origenDatos) throws Exception {

        System.out.print("Ingrese la ruta del archivo CSV con los datos: ");
        String ruta = new java.util.Scanner(System.in).nextLine();
        File archivoCSV = new File(ruta);

        // === Obtener columnas reales desde origenDatos ===
        if (!(origenDatos instanceof String columnasJsonString)) {
            throw new IllegalArgumentException("El parámetro origenDatos debe contener un JSON válido con columnas.");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode columnasArray = mapper.readTree(columnasJsonString);

        if (bd.getTipo().toString().equals("POSTGRESQL")) {

            try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV)); Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {

                String cabecera = br.readLine();
                if (cabecera == null) {
                    throw new RuntimeException("El archivo CSV está vacío.");
                }

                String[] nombresColumnas = cabecera.split(",", -1);
                if (nombresColumnas.length != columnasArray.size()) {
                    throw new RuntimeException("Número de columnas en CSV no coincide con definición esperada.");
                }

                String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",", -1);

                    if (valores.length != nombresColumnas.length) {
                        System.err.println("Fila con número incorrecto de columnas. Saltando: " + linea);
                        continue;
                    }

                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        for (int i = 0; i < valores.length; i++) {
                            String valor = valores[i].trim();
                            String tipo = columnasArray.get(i).get("tipo").asText().toLowerCase();

                            if (valor.isEmpty()) {
                                stmt.setNull(i + 1, Types.NULL);
                            } else {
                                switch (tipo) {
                                    case "entero":
                                        stmt.setInt(i + 1, Integer.parseInt(valor));
                                        break;
                                    case "decimal":
                                        stmt.setDouble(i + 1, Double.parseDouble(valor));
                                        break;
                                    case "fecha":
                                        stmt.setDate(i + 1, java.sql.Date.valueOf(LocalDate.parse(valor)));
                                        break;
                                    case "cadena":
                                    default:
                                        stmt.setString(i + 1, valor);
                                        break;
                                }
                            }
                        }
                        stmt.executeUpdate();
                        System.out.println("Fila insertada correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error al insertar fila: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                System.out.println("Carga CSV completada en tabla: " + nombreTabla);
            }

        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV)); Connection conn = ConexionMultiBDFactory.getConexion(bd)) {

                String cabecera = br.readLine();
                if (cabecera == null) {
                    throw new RuntimeException("El archivo CSV está vacío.");
                }

                String[] nombresColumnas = cabecera.split(",", -1);
                if (nombresColumnas.length != columnasArray.size()) {
                    throw new RuntimeException("Número de columnas en CSV no coincide con definición esperada.");
                }

                String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",", -1);

                    if (valores.length != nombresColumnas.length) {
                        System.err.println("Fila con número incorrecto de columnas. Saltando: " + linea);
                        continue;
                    }

                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        for (int i = 0; i < valores.length; i++) {
                            String valor = valores[i].trim();
                            String tipo = columnasArray.get(i).get("tipo").asText().toLowerCase();

                            if (valor.isEmpty()) {
                                stmt.setNull(i + 1, Types.NULL);
                            } else {
                                switch (tipo) {
                                    case "entero":
                                        stmt.setInt(i + 1, Integer.parseInt(valor));
                                        break;
                                    case "decimal":
                                        stmt.setDouble(i + 1, Double.parseDouble(valor));
                                        break;
                                    case "fecha":
                                        stmt.setDate(i + 1, java.sql.Date.valueOf(LocalDate.parse(valor)));
                                        break;
                                    case "cadena":
                                    default:
                                        stmt.setString(i + 1, valor);
                                        break;
                                }
                            }
                        }
                        stmt.executeUpdate();
                        System.out.println("Fila insertada correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error al insertar fila: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                System.out.println("Carga CSV completada en tabla: " + nombreTabla);
            }
        }

    }
}
