package com.MiNegocio.consultas;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ConsultaEstructurada {

    public void consultar(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto) throws Exception {
        String[] opciones = {"Ver todos los datos", "Filtrar por una columna", "JOIN (no disponible)"};

        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona el tipo de consulta para la tabla '" + objeto.getNombreTabla() + "':",
                "Consulta estructurada",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == -1) return;

        try (Connection conn = bd.getTipo().toString().equals("POSTGRESQL")
                ? ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                : ConexionMultiBDFactory.getConexion(bd)) {

            switch (seleccion) {
                case 0 -> ejecutarConsultaSimple(conn, objeto, bd);
                case 1 -> ejecutarConsultaFiltrada(conn, objeto, bd);
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private void ejecutarConsultaSimple(Connection conn, ObjetoBDFranquicia objeto, BaseDatosFranquicia bd) throws Exception {
        String sql = SQLHelper.generarConsultaSimple(objeto.getNombreTabla(), bd.getTipo());

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            mostrarResultadosEnTabla(rs, "Todos los datos de: " + objeto.getNombreTabla());
        }
    }

    private void ejecutarConsultaFiltrada(Connection conn, ObjetoBDFranquicia objeto, BaseDatosFranquicia bd) throws Exception {
        JsonNode columnas = new ObjectMapper().readTree(objeto.getColumnas());

        String[] nombresColumnas = new String[columnas.size()];
        for (int i = 0; i < columnas.size(); i++) {
            nombresColumnas[i] = columnas.get(i).get("nombre").asText();
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona una columna para filtrar:",
                "Filtrado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresColumnas,
                nombresColumnas[0]
        );

        if (seleccion == null) return;

        String valor = JOptionPane.showInputDialog(
                null,
                "Ingresa el valor para la columna '" + seleccion + "':",
                "Valor de filtro",
                JOptionPane.PLAIN_MESSAGE
        );

        if (valor == null || valor.isBlank()) return;

        String sql = SQLHelper.generarConsultaFiltrada(objeto.getNombreTabla(), List.of(seleccion), bd.getTipo());

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);
            ResultSet rs = stmt.executeQuery();
            mostrarResultadosEnTabla(rs, "Resultados filtrados por: " + seleccion + " = " + valor);
        }
    }

    private void mostrarResultadosEnTabla(ResultSet rs, String titulo) throws Exception {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = meta.getColumnLabel(i);
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        while (rs.next()) {
            String[] fila = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                fila[i - 1] = rs.getString(i);
            }
            model.addRow(fila);
        }

        JTable tabla = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);

        // Estética moderna
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(24);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JDialog dialog = new JDialog();
        dialog.setTitle(titulo);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(scrollPane);
        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
