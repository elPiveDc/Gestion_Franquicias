package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.repository.impl.BaseDatosRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.ObjetoBDRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.impl.ServicioCargaDatosImpl;
import com.MiNegocio.consultas.ConsultaEstructurada;
import com.MiNegocio.consultas.ConsultaIA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SelectorCargaYConsultaFrame extends JFrame {

    private final ObjetoBDRepositoryImpl objetoRepo;
    private final BaseDatosRepositoryImpl bdRepo;
    private final int idBD;
    private final String bdnombre;

    private JComboBox<String> comboTablas;
    private JComboBox<String> comboAccion; // Cargar o Consultar
    private JComboBox<String> comboSubaccion;
    private JButton btnEjecutar;
    private List<ObjetoBDFranquicia> tablas;

    private final ServicioCargaDatosImpl servicioCarga;

    public SelectorCargaYConsultaFrame(
            ObjetoBDRepositoryImpl objetoRepo,
            BaseDatosRepositoryImpl bdRepo,
            int idBD,
            String bdnombre
    ) {
        super("Módulo Carga / Consulta - " + bdnombre);
        this.objetoRepo = objetoRepo;
        this.bdRepo = bdRepo;
        this.idBD = idBD;
        this.bdnombre = bdnombre;
        this.servicioCarga = new ServicioCargaDatosImpl(objetoRepo, bdRepo);

        configurarUI();
        initComponents();
        cargarTablas();
        setupListeners();

        setSize(520, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void configurarUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    private void initComponents() {
        comboTablas = new JComboBox<>();
        comboAccion = new JComboBox<>(new String[]{"Registrar (Cargar datos)", "Consultar"});
        comboSubaccion = new JComboBox<>();
        btnEjecutar = new JButton("▶ Ejecutar");

        JLabel lblTablas = new JLabel("Selecciona tabla:");
        JLabel lblAccion = new JLabel("Selecciona acción:");
        JLabel lblSubaccion = new JLabel("Tipo de operación:");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblTablas, gbc);

        gbc.gridx = 1;
        panel.add(comboTablas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblAccion, gbc);

        gbc.gridx = 1;
        panel.add(comboAccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblSubaccion, gbc);

        gbc.gridx = 1;
        panel.add(comboSubaccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnEjecutar, gbc);

        add(panel);
    }

    private void cargarTablas() {
        tablas = objetoRepo.listarObjetosPorBD(idBD);
        comboTablas.removeAllItems();

        if (tablas.isEmpty()) {
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
            if ("Registrar (Cargar datos)".equals(accion)) {
                switch (subaccion) {
                    case "Manual" -> servicioCarga.cargarDesdeManual(seleccionada.getIdObjeto());
                    case "Desde archivo JSON" -> servicioCarga.cargarDesdeJSON(seleccionada.getIdObjeto());
                    case "Desde archivo CSV" -> servicioCarga.cargarDesdeCSV(seleccionada.getIdObjeto());
                    default -> JOptionPane.showMessageDialog(this, "Método no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                var bd = bdRepo.buscarPorId(idBD);
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