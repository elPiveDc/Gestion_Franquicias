package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConstructorTablaInteractiva {

    public static ObjetoBDFranquicia crearTablaDesdeConsola(Scanner scanner) throws Exception {

        ObjetoBDFranquicia tabla = new ObjetoBDFranquicia();
        List<Map<String, String>> columnas = new ArrayList<>();

        System.out.println("=== CREACIÓN DE TABLA ===");
        System.out.print("Nombre de la tabla: ");
        String nombreTabla = scanner.nextLine();
        tabla.setNombreTabla(nombreTabla);
        tabla.setTipoObjeto("TABLA");
        tabla.setEsTablaUsuarios(false); // Se puede personalizar según lógica

        boolean agregarOtra = true;
        while (agregarOtra) {
            Map<String, String> columna = new HashMap<>();

            System.out.print("Nombre de la columna: ");
            columna.put("nombre", scanner.nextLine());

            String tipo;
            do {
                System.out.print("Tipo (entero, cadena, decimal, fecha): ");
                tipo = scanner.nextLine().trim();
                if (tipo.isEmpty()) {
                    System.out.println(" El tipo de dato es obligatorio. Intenta nuevamente.");
                }
            } while (tipo.isEmpty());
            columna.put("tipo", tipo);

            System.out.print("Restricciones (ej: PRIMARY KEY, NOT NULL), dejar vacío si ninguna: ");
            columna.put("restricciones", scanner.nextLine());

            columnas.add(columna);

            System.out.print("¿Desea agregar otra columna? (s/n): ");
            String resp = scanner.nextLine().toLowerCase();
            agregarOtra = resp.equals("s");
        }

        // Convertir a JSON usando Jackson
        ObjectMapper mapper = new ObjectMapper();
        String columnasJson = mapper.writeValueAsString(columnas);
        tabla.setColumnas(columnasJson);
        tabla.setFechaCreacion(new Date());

        return tabla;
    }

    public static ObjetoBDFranquicia crearTablaInteractiva() throws Exception {
        
        JFrame frame = new JFrame("Crear Tabla");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Campo para el nombre de la tabla
        JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Nombre de la tabla:");
        JTextField nombreTablaField = new JTextField(30);
        nombrePanel.add(label);
        nombrePanel.add(nombreTablaField);

        // Definición de columnas
        String[] columnNames = {"Nombre", "Tipo", "Restricciones"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        JTable tablaColumnas = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        // Opciones para ComboBoxes
        String[] tiposDatos = {"entero", "cadena", "decimal", "fecha"};
        String[] restriccionesOpcionales = {"", "PRIMARY KEY", "NOT NULL", "UNIQUE"};

        // Asignar ComboBox como editor para columnas específicas
        JComboBox<String> tipoComboBox = new JComboBox<>(tiposDatos);
        tablaColumnas.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(tipoComboBox));

        JComboBox<String> restriccionComboBox = new JComboBox<>(restriccionesOpcionales);
        tablaColumnas.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(restriccionComboBox));

        // Decoración visual moderna
        tablaColumnas.setRowHeight(28);
        tablaColumnas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tablaColumnas.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaColumnas.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tablaColumnas);

        // Panel de botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnAgregar = new JButton("Agregar Fila");
        JButton btnEliminar = new JButton("Eliminar Fila");
        JButton btnCrear = new JButton("Crear Tabla");
        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEliminar);
        botonesPanel.add(btnCrear);

        frame.add(nombrePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(botonesPanel, BorderLayout.SOUTH);

        // Acciones
        btnAgregar.addActionListener(e -> tableModel.addRow(new Object[]{"", tiposDatos[0], ""}));

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaColumnas.getSelectedRow();
            if (filaSeleccionada != -1) {
                tableModel.removeRow(filaSeleccionada);
            }
        });

        final ObjetoBDFranquicia[] resultado = new ObjetoBDFranquicia[1];

        btnCrear.addActionListener(e -> {
            String nombreTabla = nombreTablaField.getText().trim();
            if (nombreTabla.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El nombre de la tabla es obligatorio.");
                return;
            }

            List<Map<String, String>> columnasList = new ArrayList<>();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String nombre = Objects.toString(tableModel.getValueAt(i, 0), "").trim();
                String tipo = Objects.toString(tableModel.getValueAt(i, 1), "").trim();
                String restricciones = Objects.toString(tableModel.getValueAt(i, 2), "").trim();

                if (nombre.isEmpty() || tipo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nombre y tipo son obligatorios en todas las filas.");
                    return;
                }

                Map<String, String> col = new HashMap<>();
                col.put("nombre", nombre);
                col.put("tipo", tipo);
                col.put("restricciones", restricciones);
                columnasList.add(col);
            }

            try {
                ObjetoBDFranquicia tabla = new ObjetoBDFranquicia();
                tabla.setNombreTabla(nombreTabla);
                tabla.setTipoObjeto("TABLA");
                tabla.setEsTablaUsuarios(false);
                tabla.setFechaCreacion(new Date());

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(columnasList);
                tabla.setColumnas(json);

                resultado[0] = tabla;
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al crear tabla: " + ex.getMessage());
            }
        });

        frame.setVisible(true);

        // Esperar hasta que se cierre la ventana
        while (frame.isDisplayable()) {
            Thread.sleep(100);
        }

        return resultado[0]; // Puede ser null si se canceló
    }
}