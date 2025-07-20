package com.MiNegocio.configuracioncentral.integration.cargadatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.function.BooleanSupplier;

public class CargadorDatosJSON implements CargadorDatos {

    @Override
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object columnasData) throws Exception {
        Component parent = null;

        // Mostrar JFileChooser para seleccionar el archivo JSON
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo JSON");
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            mostrarInfo(null, "Carga cancelada por el usuario.");
            return;
        }

        File archivoJSON = fileChooser.getSelectedFile();
        if (!archivoJSON.exists()) {
            mostrarError(null, "El archivo no existe.");
            return;
        }

        // Mostrar barra de progreso y botón de cancelar
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);

        JButton botonCancelar = new JButton("Cancelar");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(barraProgreso, BorderLayout.CENTER);
        panel.add(botonCancelar, BorderLayout.SOUTH);

        JDialog dialog = new JDialog((Frame) null, "Cargando JSON...", Dialog.ModalityType.MODELESS);
        dialog.getContentPane().add(panel);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        final boolean[] cancelado = {false};
        botonCancelar.addActionListener(e -> {
            cancelado[0] = true;
            botonCancelar.setEnabled(false);
        });

        new Thread(() -> {
            try {
                cargarDesdeArchivo(bd, nombreTabla, columnasData, archivoJSON, parent, barraProgreso, () -> cancelado[0]);
            } catch (Exception ex) {
                mostrarError(null, "Error al cargar JSON: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                dialog.dispose();
            }
        }).start();
    }

    protected void cargarDesdeArchivo(BaseDatosFranquicia bd, String nombreTabla, Object columnasData, File archivoJSON,
            Component parent, JProgressBar barraProgreso, BooleanSupplier cancelado) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode columnasArray;
        if (columnasData instanceof String) {
            columnasArray = mapper.readTree((String) columnasData);
        } else if (columnasData instanceof List) {
            columnasArray = mapper.valueToTree(columnasData);
        } else {
            throw new IllegalArgumentException("El parámetro columnasData debe ser un JSON válido o una lista.");
        }

        JsonNode filas = mapper.readTree(archivoJSON);
        if (!filas.isArray()) {
            mostrarError(parent, "El archivo debe contener un array JSON de objetos.");
            return;
        }

        try (
                Connection conn = bd.getTipo().toString().equals("POSTGRESQL")
                ? ConexionMultifactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                : ConexionMultifactory.getConexion(bd)) {
            String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

            int total = filas.size();
            int insertadas = 0;

            for (int i = 0; i < total; i++) {
                if (cancelado.getAsBoolean()) {
                    mostrarInfo(parent, "Carga cancelada. Filas insertadas: " + insertadas);
                    return;
                }

                JsonNode fila = filas.get(i);
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    int j = 1;
                    for (JsonNode columna : columnasArray) {
                        String nombre = columna.get("nombre").asText();
                        String tipo = columna.get("tipo").asText().toLowerCase();

                        JsonNode valor = fila.get(nombre);
                        if (valor == null || valor.isNull()) {
                            stmt.setNull(j, Types.NULL);
                        } else {
                            switch (tipo) {
                                case "entero" ->
                                    stmt.setInt(j, valor.asInt());
                                case "decimal" ->
                                    stmt.setDouble(j, valor.asDouble());
                                case "fecha" ->
                                    stmt.setDate(j, java.sql.Date.valueOf(valor.asText()));
                                default ->
                                    stmt.setString(j, valor.asText());
                            }
                        }
                        j++;
                    }
                    stmt.executeUpdate();
                    insertadas++;
                } catch (Exception e) {
                    System.err.println("Error al insertar fila: " + e.getMessage());
                    e.printStackTrace();
                }

                if (barraProgreso != null) {
                    barraProgreso.setValue((i + 1) * 100 / total);
                }
            }

            mostrarInfo(parent, "Carga finalizada correctamente. Filas insertadas: " + insertadas);
        }
    }

    protected void mostrarError(Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected void mostrarInfo(Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
