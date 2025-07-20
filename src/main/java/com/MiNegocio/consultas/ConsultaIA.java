package com.MiNegocio.consultas;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.service.IAService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ConsultaIA {

    public void consultar(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto) {
        String pregunta = JOptionPane.showInputDialog(
                null,
                "Describe lo que deseas consultar en lenguaje natural:",
                "Consulta IA - " + objeto.getNombreTabla(),
                JOptionPane.QUESTION_MESSAGE
        );

        if (pregunta == null || pregunta.isBlank()) return;

        try {
            String sql = generarSQLDesdePregunta(pregunta, objeto, bd);

            if (sql == null || sql.isBlank()) {
                JOptionPane.showMessageDialog(null, "No se pudo generar una consulta válida.", "Consulta IA", JOptionPane.WARNING_MESSAGE);
                return;
            }

            System.out.println("Consulta generada: " + sql); // solo para debugging

            try (
                Connection conn = bd.getTipo().toString().equals("POSTGRESQL")
                        ? ConexionMultifactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                        : ConexionMultifactory.getConexion(bd);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
            ) {
                mostrarResultados(rs, "Consulta IA: " + pregunta);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrió un error durante la consulta:\n" + e.getMessage(),
                    "Error de consulta IA",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
            case "POSTGRESQL" -> "PostgreSQL (usa LIMIT, tipos como SERIAL, TEXT)";
            case "MYSQL" -> "MySQL (usa LIMIT, tipos como INT, VARCHAR)";
            case "ORACLE" -> "Oracle SQL (usa ROWNUM, tipos como VARCHAR2, NUMBER)";
            default -> bd.getTipo().name();
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

        // Filtrar la primera línea que contenga SQL si hay varias
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

    private void mostrarResultados(ResultSet rs, String titulo) throws Exception {
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
