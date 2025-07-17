package com.MiNegocio.configuracioncentral.integration.cargadatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class CargadorDatosCSV implements CargadorDatos {

    @Override
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object origenDatos) throws Exception {
        Component parent = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo CSV");
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            mostrarInfo(null, "Carga cancelada por el usuario.");
            return;
        }

        File archivoCSV = fileChooser.getSelectedFile();
        if (!archivoCSV.exists()) {
            mostrarError(null, "El archivo no existe.");
            return;
        }

        // Mostrar diálogo de progreso
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);

        JButton botonCancelar = new JButton("Cancelar");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(barraProgreso, BorderLayout.CENTER);
        panel.add(botonCancelar, BorderLayout.SOUTH);

        JDialog dialog = new JDialog((Frame) null, "Cargando CSV...", Dialog.ModalityType.MODELESS);
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
                cargarDesdeArchivo(bd, nombreTabla, origenDatos, archivoCSV, parent, barraProgreso, () -> cancelado[0]);
            } catch (Exception ex) {
                mostrarError(null, "Error al cargar CSV: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                dialog.dispose();
            }
        }).start();
    }

    protected void cargarDesdeArchivo(BaseDatosFranquicia bd, String nombreTabla, Object origenDatos, File archivoCSV,
                                      Component parent, JProgressBar barraProgreso, BooleanSupplier cancelado) throws Exception {

        if (!(origenDatos instanceof String columnasJsonString)) {
            throw new IllegalArgumentException("El parámetro origenDatos debe ser un JSON válido.");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode columnasArray = mapper.readTree(columnasJsonString);

        try (
            BufferedReader br = new BufferedReader(new FileReader(archivoCSV));
            Connection conn = bd.getTipo().toString().equals("POSTGRESQL")
                ? ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                : ConexionMultiBDFactory.getConexion(bd)
        ) {
            String cabecera = br.readLine();
            if (cabecera == null) {
                mostrarError(parent, "El archivo está vacío.");
                return;
            }

            String[] nombresColumnas = cabecera.split(",", -1);
            if (nombresColumnas.length != columnasArray.size()) {
                mostrarError(parent, "Columnas del CSV no coinciden con definición esperada.");
                return;
            }

            String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

            List<String> filas = br.lines().collect(Collectors.toList());
            int total = filas.size();
            int insertadas = 0;

            for (int i = 0; i < total; i++) {
                if (cancelado.getAsBoolean()) {
                    mostrarInfo(parent, "Carga cancelada. Filas insertadas: " + insertadas);
                    return;
                }

                String linea = filas.get(i);
                String[] valores = linea.split(",", -1);

                if (valores.length != nombresColumnas.length) {
                    System.err.println("Saltando fila inválida: " + linea);
                    continue;
                }

                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    for (int j = 0; j < valores.length; j++) {
                        String valor = valores[j].trim();
                        String tipo = columnasArray.get(j).get("tipo").asText().toLowerCase();

                        if (valor.isEmpty()) {
                            stmt.setNull(j + 1, Types.NULL);
                        } else {
                            switch (tipo) {
                                case "entero" -> stmt.setInt(j + 1, Integer.parseInt(valor));
                                case "decimal" -> stmt.setDouble(j + 1, Double.parseDouble(valor));
                                case "fecha" -> stmt.setDate(j + 1, java.sql.Date.valueOf(LocalDate.parse(valor)));
                                default -> stmt.setString(j + 1, valor);
                            }
                        }
                    }
                    stmt.executeUpdate();
                    insertadas++;
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