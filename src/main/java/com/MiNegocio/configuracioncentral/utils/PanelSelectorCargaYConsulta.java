package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.repository.impl.BaseDatosRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.ObjetoBDRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.impl.ServicioCargaDatosImpl;
import com.MiNegocio.consultas.ConsultaEstructurada;
import com.MiNegocio.consultas.ConsultaIA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelSelectorCargaYConsulta extends JPanel {

    private final ObjetoBDRepositoryImpl objetoRepo;
    private final BaseDatosRepositoryImpl bdRepo;
    private final ServicioCargaDatosImpl servicioCarga;

    private final int idfran;
    private final String bdnombre;

    private JComboBox<String> comboBDs;
    private JComboBox<String> comboTablas;
    private JComboBox<String> comboAccion;
    private JComboBox<String> comboSubaccion;
    private JButton btnEjecutar;

    private List<ObjetoBDFranquicia> tablas;
    private final Map<String, Integer> mapaNombreIdBD = new HashMap<>();
    private Integer idBDSeleccionada = null;

    public PanelSelectorCargaYConsulta(ObjetoBDRepositoryImpl objetoRepo,
                                       BaseDatosRepositoryImpl bdRepo,
                                       int idfran,
                                       String bdnombre) {
        this.objetoRepo = objetoRepo;
        this.bdRepo = bdRepo;
        this.idfran = idfran;
        this.bdnombre = bdnombre;
        this.servicioCarga = new ServicioCargaDatosImpl(objetoRepo, bdRepo);
        configurarUI();
        initComponents();
        cargarBasesDatos();
        setupListeners();
    }

    private void configurarUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Módulo Carga / Consulta - " + bdnombre));
        setBackground(Color.WHITE);

        comboBDs = new JComboBox<>();
        comboTablas = new JComboBox<>();
        comboAccion = new JComboBox<>(new String[]{"Registrar (Cargar datos)", "Consultar"});
        comboSubaccion = new JComboBox<>();
        btnEjecutar = new JButton("Ejecutar");

        JLabel lblBD = new JLabel("Selecciona Base de Datos:");
        JLabel lblTablas = new JLabel("Selecciona tabla:");
        JLabel lblAccion = new JLabel("Selecciona acción:");
        JLabel lblSubaccion = new JLabel("Tipo de operación:");

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        lblBD.setFont(labelFont);
        lblTablas.setFont(labelFont);
        lblAccion.setFont(labelFont);
        lblSubaccion.setFont(labelFont);

        Font comboFont = new Font("Segoe UI", Font.PLAIN, 13);
        comboBDs.setFont(comboFont);
        comboTablas.setFont(comboFont);
        comboAccion.setFont(comboFont);
        comboSubaccion.setFont(comboFont);
        btnEjecutar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEjecutar.setBackground(new Color(60, 130, 200));
        btnEjecutar.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblBD, gbc);
        gbc.gridx = 1;
        add(comboBDs, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblTablas, gbc);
        gbc.gridx = 1;
        add(comboTablas, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblAccion, gbc);
        gbc.gridx = 1;
        add(comboAccion, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(lblSubaccion, gbc);
        gbc.gridx = 1;
        add(comboSubaccion, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnEjecutar, gbc);
    }

    private void cargarBasesDatos() {
        try {
            var listaBDs = bdRepo.obtenerPorFranquicia(idfran); // Asegúrate que este método devuelve todas las BDs necesarias
            comboBDs.removeAllItems();
            mapaNombreIdBD.clear();

            for (BaseDatosFranquicia bd : listaBDs) {
                comboBDs.addItem(bd.getNombreBD());
                mapaNombreIdBD.put(bd.getNombreBD(), bd.getId());
            }

            if (!listaBDs.isEmpty()) {
                comboBDs.setSelectedIndex(0);
                idBDSeleccionada = mapaNombreIdBD.get(comboBDs.getSelectedItem());
                cargarTablas(); // Cargar tablas para la primera base de datos
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar bases de datos: " + ex.getMessage());
        }
    }

    private void cargarTablas() {
        if (idBDSeleccionada == null) return;
        tablas = objetoRepo.listarObjetosPorBD(idBDSeleccionada);
        comboTablas.removeAllItems();

        if (tablas == null || tablas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tablas disponibles aún.", "Información", JOptionPane.INFORMATION_MESSAGE);
            btnEjecutar.setEnabled(false);
        } else {
            for (ObjetoBDFranquicia obj : tablas) {
                comboTablas.addItem(obj.getNombreTabla());
            }
            btnEjecutar.setEnabled(true);
        }
    }

    private void setupListeners() {
        comboBDs.addActionListener(e -> {
            String seleccion = (String) comboBDs.getSelectedItem();
            if (seleccion != null) {
                idBDSeleccionada = mapaNombreIdBD.get(seleccion);
                cargarTablas();
            }
        });

        comboAccion.addActionListener(e -> actualizarSubacciones());

        btnEjecutar.addActionListener(this::onEjecutar);

        actualizarSubacciones();
    }

    private void actualizarSubacciones() {
        comboSubaccion.removeAllItems();
        String accion = (String) comboAccion.getSelectedItem();

        if ("Registrar (Cargar datos)".equals(accion)) {
            comboSubaccion.addItem("Manual");
            comboSubaccion.addItem("Desde archivo JSON");
            comboSubaccion.addItem("Desde archivo CSV");
        } else {
            comboSubaccion.addItem("Consulta estructurada");
            comboSubaccion.addItem("Consulta por lenguaje natural (IA)");
        }
    }

    private void onEjecutar(ActionEvent e) {
        String nombreTabla = (String) comboTablas.getSelectedItem();
        String accion = (String) comboAccion.getSelectedItem();
        String subaccion = (String) comboSubaccion.getSelectedItem();
        ObjetoBDFranquicia seleccionada = buscarTablaPorNombre(nombreTabla);

        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una tabla válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            var bd = bdRepo.buscarPorId(idBDSeleccionada);
            if ("Registrar (Cargar datos)".equals(accion)) {
                switch (subaccion) {
                    case "Manual" -> servicioCarga.cargarDesdeManual(seleccionada.getIdObjeto());
                    case "Desde archivo JSON" -> servicioCarga.cargarDesdeJSON(seleccionada.getIdObjeto());
                    case "Desde archivo CSV" -> servicioCarga.cargarDesdeCSV(seleccionada.getIdObjeto());
                    default -> JOptionPane.showMessageDialog(this, "Método no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                switch (subaccion) {
                    case "Consulta estructurada" -> new ConsultaEstructurada().consultar(bd, seleccionada);
                    case "Consulta por lenguaje natural (IA)" -> new ConsultaIA().consultar(bd, seleccionada);
                    default -> JOptionPane.showMessageDialog(this, "Consulta no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private ObjetoBDFranquicia buscarTablaPorNombre(String nombre) {
        if (nombre == null) return null;
        return tablas.stream()
                .filter(obj -> obj.getNombreTabla().equals(nombre))
                .findFirst()
                .orElse(null);
    }
}