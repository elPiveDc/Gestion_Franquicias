package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.application.RegistroInicialService;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.impl.BaseDatosRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.FranquiciaRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.ObjetoBDRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.UsuarioRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.impl.BaseDatosServiceImpl;
import com.MiNegocio.configuracioncentral.service.impl.FranquiciaServiceImpl;
import com.MiNegocio.configuracioncentral.service.impl.ObjetoBDServiceImpl;
import com.MiNegocio.configuracioncentral.service.impl.UsuarioServiceImpl;
import com.MiNegocio.interfazgrafica.VentanaPrincipalAdmin;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PanelCrearTabla extends JPanel {

    // ==== REPOSITORIOS ====
    UsuarioRepositoryImpl usuarioRepo = new UsuarioRepositoryImpl();
    FranquiciaRepositoryImpl franquiciaRepo = new FranquiciaRepositoryImpl();
    BaseDatosRepositoryImpl bdRepo = new BaseDatosRepositoryImpl();
    ObjetoBDRepositoryImpl objetoRepo = new ObjetoBDRepositoryImpl();

    // ==== SERVICIOS ====
    UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioRepo);
    FranquiciaServiceImpl franquiciaService = new FranquiciaServiceImpl(franquiciaRepo, usuarioRepo);
    BaseDatosServiceImpl bdService = new BaseDatosServiceImpl(bdRepo, franquiciaRepo);
    ObjetoBDServiceImpl objetoService = new ObjetoBDServiceImpl(
            objetoRepo, bdRepo, usuarioRepo, new ConexionMultiBDFactory(), new GestorObjetosFactory()
    );
    
    private final VentanaPrincipalAdmin ventanaPrincipal;
    private final int parametro;
    private JTable tablaColumnas;
    private DefaultTableModel tableModel;
    private JTextField nombreTablaField;

    private JComboBox<String> comboBasesDatos;
    private Map<String, Integer> mapaNombreIdBD = new HashMap<>();
    private Integer idBaseDatosSeleccionada = null;

    public PanelCrearTabla(int parametro, VentanaPrincipalAdmin ventanaPrincipal) {
        this.parametro = parametro;
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // === Panel Superior ===
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(Color.WHITE);

        // === Selección de Base de Datos ===
        JPanel bdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bdPanel.setBackground(Color.WHITE);
        JLabel bdLabel = new JLabel("Seleccionar Base de Datos:");
        bdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBasesDatos = new JComboBox<>();
        comboBasesDatos.setPreferredSize(new Dimension(250, 28));
        bdPanel.add(bdLabel);
        bdPanel.add(comboBasesDatos);
        panelSuperior.add(bdPanel);
        cargarBasesDatosFranquicia();

        comboBasesDatos.addActionListener(e -> {
            String seleccion = (String) comboBasesDatos.getSelectedItem();
            if (seleccion != null) {
                idBaseDatosSeleccionada = mapaNombreIdBD.get(seleccion);
            }
        });

        // === Nombre de la tabla ===
        JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nombrePanel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Nombre de la tabla:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nombreTablaField = new JTextField(30);
        nombreTablaField.setPreferredSize(new Dimension(250, 28));
        nombrePanel.add(label);
        nombrePanel.add(nombreTablaField);
        panelSuperior.add(nombrePanel);

        // === Tabla de columnas ===
        String[] columnNames = {"Nombre", "Tipo", "Restricciones"};
        tableModel = new DefaultTableModel(null, columnNames);
        tablaColumnas = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        String[] tiposDatos = {"entero", "cadena", "decimal", "fecha"};
        String[] restriccionesOpcionales = {"", "PRIMARY KEY", "NOT NULL", "UNIQUE"};
        tablaColumnas.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<>(tiposDatos)));
        tablaColumnas.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<>(restriccionesOpcionales)));

        tablaColumnas.setRowHeight(28);
        tablaColumnas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaColumnas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaColumnas.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaColumnas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Definición de columnas"));
        scrollPane.setBackground(Color.WHITE);

        // === Panel de botones ===
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setBackground(Color.WHITE);
        JButton btnAgregar = crearBoton("➕ Agregar Fila");
        JButton btnEliminar = crearBoton("🗑️ Eliminar Fila");
        JButton btnCrear = crearBoton("✅ Crear Tabla");

        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEliminar);
        botonesPanel.add(btnCrear);

        btnAgregar.addActionListener(e -> tableModel.addRow(new Object[]{"", tiposDatos[0], ""}));

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaColumnas.getSelectedRow();
            if (filaSeleccionada != -1) {
                tableModel.removeRow(filaSeleccionada);
            }
        });

        btnCrear.addActionListener(e -> {
            crearTabla();
            cargarBasesDatosFranquicia(); // Recargar BD tras creación
        });

        // === Agregar todo al panel principal ===
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);
    }

    private void cargarBasesDatosFranquicia() {
        try {
            var listaBDs = bdService.listarBasesDatosPorFranquicia(parametro);
            if (listaBDs == null || listaBDs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay bases de datos configuradas para esta franquicia.");
                comboBasesDatos.setEnabled(false);
                idBaseDatosSeleccionada = null;
                return;
            }

            comboBasesDatos.setEnabled(true);
            comboBasesDatos.removeAllItems();
            mapaNombreIdBD.clear();

            for (var bd : listaBDs) {
                comboBasesDatos.addItem(bd.getNombreBD());
                mapaNombreIdBD.put(bd.getNombreBD(), bd.getId());
            }

            comboBasesDatos.setSelectedIndex(0);
            idBaseDatosSeleccionada = mapaNombreIdBD.get(comboBasesDatos.getSelectedItem());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar bases de datos: " + ex.getMessage());
        }
    }

    private void crearTabla() {
        String nombreTabla = nombreTablaField.getText().trim();
        if (nombreTabla.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la tabla es obligatorio.");
            return;
        }

        if (idBaseDatosSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una base de datos para crear la tabla.");
            return;
        }

        List<Map<String, String>> columnasList = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String nombre = Objects.toString(tableModel.getValueAt(i, 0), "").trim();
            String tipo = Objects.toString(tableModel.getValueAt(i, 1), "").trim();
            String restricciones = Objects.toString(tableModel.getValueAt(i, 2), "").trim();

            if (nombre.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y tipo son obligatorios en todas las filas.");
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

            objetoService.crearObjeto(idBaseDatosSeleccionada, tabla);

            JOptionPane.showMessageDialog(this, "✅ Tabla creada exitosamente.");
            nombreTablaField.setText("");
            tableModel.setRowCount(0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error al crear tabla: " + ex.getMessage());
        }
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(60, 130, 200));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(140, 35));
        return btn;
    }
}
