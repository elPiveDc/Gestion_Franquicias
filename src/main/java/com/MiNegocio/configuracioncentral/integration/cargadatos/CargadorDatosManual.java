package com.MiNegocio.configuracioncentral.integration.cargadatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMongoDB;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

public class CargadorDatosManual implements CargadorDatos {

    @Override
    public void cargar(BaseDatosFranquicia bd, String nombreTabla, Object columnasData) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode columnasArray;

        if (columnasData instanceof String) {
            columnasArray = mapper.readTree((String) columnasData);
        } else if (columnasData instanceof List) {
            columnasArray = mapper.valueToTree(columnasData);
        } else {
            throw new IllegalArgumentException("Formato de columnas no reconocido");
        }

        // Crear ventana principal
        JFrame frame = new JFrame("Ingreso Manual de Datos - " + nombreTabla);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        // Crear modelo de tabla
        String[] nombresColumnas = new String[columnasArray.size()];
        for (int i = 0; i < columnasArray.size(); i++) {
            nombresColumnas[i] = columnasArray.get(i).get("nombre").asText();
        }

        DefaultTableModel modeloTabla = new DefaultTableModel(nombresColumnas, 0);
        JTable tabla = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tabla);
        frame.add(scroll, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();

        JButton btnAgregarFila = new JButton("Agregar fila");
        JButton btnGuardar = new JButton("Guardar");

        btnAgregarFila.addActionListener(e -> modeloTabla.addRow(new Object[nombresColumnas.length]));

        btnGuardar.addActionListener(e -> {
            try (
                Connection conn = bd.getTipo().toString().equals("POSTGRESQL")
                        ? ConexionMultifactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                        : ConexionMultifactory.getConexion(bd)
            ) {
                String insertSQL = SQLHelper.generarInsertSQL(nombreTabla, columnasArray.toString(), bd.getTipo());

                int filas = modeloTabla.getRowCount();
                int insertadas = 0;

                for (int i = 0; i < filas; i++) {
                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        for (int j = 0; j < columnasArray.size(); j++) {
                            String tipo = columnasArray.get(j).get("tipo").asText().toLowerCase();
                            Object valor = modeloTabla.getValueAt(i, j);

                            if (valor == null || valor.toString().trim().isEmpty()) {
                                stmt.setNull(j + 1, Types.NULL);
                            } else {
                                switch (tipo) {
                                    case "entero" -> stmt.setInt(j + 1, Integer.parseInt(valor.toString()));
                                    case "decimal" -> stmt.setDouble(j + 1, Double.parseDouble(valor.toString()));
                                    case "fecha" -> stmt.setDate(j + 1, java.sql.Date.valueOf(valor.toString()));
                                    default -> stmt.setString(j + 1, valor.toString());
                                }
                            }
                        }
                        stmt.executeUpdate();
                        insertadas++;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error al insertar fila " + (i + 1) + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }

                JOptionPane.showMessageDialog(frame, "Se insertaron " + insertadas + " filas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        panelBotones.add(btnAgregarFila);
        panelBotones.add(btnGuardar);
        frame.add(panelBotones, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
