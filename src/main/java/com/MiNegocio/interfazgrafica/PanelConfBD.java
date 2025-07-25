package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.utils.ValidacionTablaUsuariosService;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelConfBD extends javax.swing.JPanel {

    private VentanaPrincipalCreacion ventanaPrincipal;
    int idFranquicia;
    private BaseDatosFranquicia bases;
    private List<BaseDatosFranquicia> ListadeBases;
    String nomfran;
    String vienecreacion;

    public int getIdFranquicia() {
        return idFranquicia;
    }

    public void setIdFranquicia(int idFranquicia) {
        this.idFranquicia = idFranquicia;
    }

    FranquiciaRepositoryImpl franquiciaRepository;

    public PanelConfBD(VentanaPrincipalCreacion ventanaPrincipal, int idFranquicia, String nomfran) {
        initComponents();
        this.vienecreacion = "creacion";
        this.nomfran = nomfran;
        this.franquiciaRepository = new FranquiciaRepositoryImpl();
        this.idFranquicia = idFranquicia;
        this.ventanaPrincipal = ventanaPrincipal;
        this.ListadeBases = new ArrayList<>();
        Scroloculto.setVisible(false);
        TablaOculta.setVisible(false);
        LabelOculto.setVisible(false);
        setIconoRedimensionadoToggle(btnVer, "/ojo.png", 20, 20, 0);
    }

    public PanelConfBD(List<BaseDatosFranquicia> lista, int idFranquicia, String nomfran) {
        this.vienecreacion = "adios";
        this.nomfran = nomfran;
        this.franquiciaRepository = new FranquiciaRepositoryImpl();
        this.idFranquicia = idFranquicia;
        initComponents();
        llenarTablaDesdeLista(lista, TablaOculta);
        setIconoRedimensionadoToggle(btnVer, "/ojo.png", 20, 20, 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();
        txtNombreBD = new javax.swing.JTextField();
        cbTipoBD = new javax.swing.JComboBox<>();
        txtUsuarioBD = new javax.swing.JTextField();
        txtContraseñaBD = new javax.swing.JPasswordField();
        Scroloculto = new javax.swing.JScrollPane();
        TablaOculta = new javax.swing.JTable();
        btnAñadirBD = new javax.swing.JButton();
        cbEstadoBD = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBDs = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        LabelOculto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnVer = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(253, 253, 253));
        setMaximumSize(new java.awt.Dimension(800, 550));
        setMinimumSize(new java.awt.Dimension(800, 550));
        setPreferredSize(new java.awt.Dimension(800, 550));

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel2.setText("Nombre BD:");

        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel4.setText("Tipo de BD:");

        jLabel5.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel5.setText("Usuario BD:");

        jLabel6.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel6.setText("Contraseña BD:");

        btnFinalizar.setBackground(new java.awt.Color(47, 90, 156));
        btnFinalizar.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 15)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setText("Finalizar Creación");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        txtNombreBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N

        cbTipoBD.setBackground(new java.awt.Color(253, 253, 253));
        cbTipoBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        cbTipoBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MYSQL", "POSTGRESQL", "ORACLE" }));

        txtUsuarioBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N

        txtContraseñaBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N

        TablaOculta.setBackground(new java.awt.Color(253, 253, 253));
        TablaOculta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre BD", "Tipo BD", "Estado", "URL", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Scroloculto.setViewportView(TablaOculta);

        btnAñadirBD.setBackground(new java.awt.Color(47, 90, 156));
        btnAñadirBD.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 15)); // NOI18N
        btnAñadirBD.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirBD.setText("Agregar Base de Datos");
        btnAñadirBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirBDActionPerformed(evt);
            }
        });

        cbEstadoBD.setBackground(new java.awt.Color(253, 253, 253));
        cbEstadoBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        cbEstadoBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONFIGURADA", "NO_CONFIGURADA", "ERROR" }));

        tablaBDs.setBackground(new java.awt.Color(253, 253, 253));
        tablaBDs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre BD", "Tipo BD", "Estado", "URL", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaBDs);

        jLabel3.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel3.setText("Tus Nuevas Bases de Datos:");

        LabelOculto.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        LabelOculto.setText("Bases de Datos que ya Tienes:");

        jPanel1.setBackground(new java.awt.Color(47, 90, 156));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 100));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Configurar Bases de Datos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel7.setText("Estado de la BD:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnVer.setBorderPainted(false);
        btnVer.setContentAreaFilled(false);
        btnVer.setFocusPainted(false);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbEstadoBD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreBD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoBD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioBD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtContraseñaBD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVer))
                    .addComponent(btnAñadirBD, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelOculto, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                            .addComponent(Scroloculto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuarioBD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtContraseñaBD))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEstadoBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAñadirBD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(LabelOculto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Scroloculto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            registrarBases(ListadeBases, idFranquicia);
            if ("creacion".equals(vienecreacion)) {
                UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();

                int idBDSeleccionada = preguntarEnQueBDCrearUsuarios(idFranquicia);

                // Obtener el usuario creador
                Usuario creador = usuarioRepository.obtenerCreadorDeFranquicia(idFranquicia);

                // Crear la tabla e insertar al creador
                crearTablaUsuariosPorDefecto(idBDSeleccionada, creador);

                JOptionPane.showMessageDialog(
                        null,
                        "Su(s) base(s) de datos fue/ fueron creada(s) correctamente.\n"
                        + "La tabla 'usuarios' fue creada correctamente con el usuario inicial.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );

                System.out.println("Usuario y negocio registrados correctamente.");
                VentanaPrincipalAdmin v1 = new VentanaPrincipalAdmin();
                v1.setLocationRelativeTo(null);
                v1.setVisible(true);
                v1.setNombreFran(nomfran);
                ventanaPrincipal.cerrar();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Su(s) base(s) de datos fue/ fueron creada(s) correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(PanelConfBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnAñadirBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirBDActionPerformed
        agregarBaseDatos(txtNombreBD, cbTipoBD, txtUsuarioBD, txtContraseñaBD, cbEstadoBD, tablaBDs);
    }//GEN-LAST:event_btnAñadirBDActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        if (btnVer.isSelected()) {
            // Mostrar contraseña
            txtContraseñaBD.setEchoChar((char) 0); // Sin ocultar caracteres
            btnVer.setToolTipText("Ocultar contraseña");
        } else {
            // Ocultar contraseña
            txtContraseñaBD.setEchoChar('•'); // Usar un símbolo de punto o asterisco
            btnVer.setToolTipText("Mostrar contraseña");
        }
    }//GEN-LAST:event_btnVerActionPerformed

    public void setIconoRedimensionadoToggle(javax.swing.JToggleButton boton, String rutaImagen, int ancho, int alto, int gapTexto) {
        try {
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagen = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagen));
            boton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            boton.setIconTextGap(gapTexto);
        } catch (NullPointerException e) {
            System.err.println("No se encontró la imagen: " + rutaImagen);
        }
    }

    public void llenarTablaDesdeLista(List<BaseDatosFranquicia> listaBDs, JTable tablaBDs) {
        try {
            if (listaBDs == null || listaBDs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La lista de bases de datos está vacía o es nula.");
                return;
            }

            DefaultTableModel modelo = (DefaultTableModel) tablaBDs.getModel();
            modelo.setRowCount(0); // Limpia la tabla antes de llenarla

            for (BaseDatosFranquicia bd : listaBDs) {
                if (bd == null) {
                    continue;
                }

                String nombreBD = bd.getNombreBD().trim();
                String tipoBDStr = bd.getTipo().toString().toUpperCase();

                // Evitar registros incompletos
                if (nombreBD.isEmpty() || bd.getUsuarioBD().isEmpty() || bd.getPasswordHash().isEmpty()) {
                    continue;
                }

                // Verificar duplicados ya cargados en el modelo
                boolean duplicado = false;

                for (int i = 0; i < modelo.getRowCount(); i++) {
                    String nombreExistente = modelo.getValueAt(i, 0).toString().trim();
                    String tipoExistente = modelo.getValueAt(i, 1).toString().trim().toUpperCase();

                    if (nombreBD.equalsIgnoreCase(nombreExistente) && tipoBDStr.equalsIgnoreCase(tipoExistente)) {
                        duplicado = true;
                        break;
                    }
                }

                if (!duplicado) {
                    modelo.addRow(new Object[]{
                        bd.getNombreBD(),
                        bd.getTipo(),
                        bd.getEstado(),
                        bd.getUrlConexion(),
                        bd.getUsuarioBD()
                    });
                }
            }

            JOptionPane.showMessageDialog(null, "Tabla cargada correctamente desde la lista.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + ex.getMessage());
        }
    }

    public String obtenerURLBD(JComboBox<String> cbTipoBD, JTextField txtNombreBD) {
        String tipoBD = cbTipoBD.getSelectedItem().toString();
        String nombreBD = txtNombreBD.getText().trim();
        String url = "";

        switch (tipoBD.toLowerCase()) {
            case "postgresql":
                url = "jdbc:postgresql://localhost:5432/" + nombreBD.toLowerCase();
                break;
            case "mysql":
                url = "jdbc:mysql://localhost:3306/" + nombreBD;
                break;
            case "oracle":
                url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
                break;
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado: " + tipoBD);
        }

        return url;
    }

    public void agregarBaseDatos(JTextField txtNombreBD, JComboBox<String> cbTipoBD,
            JTextField txtUsuarioBD, JTextField txtContraseñaBD,
            JComboBox<String> cbEstadoBD, JTable tablaBDs) {
        try {
            String nombreBD = txtNombreBD.getText().trim();
            String tipoBDStr = cbTipoBD.getSelectedItem().toString().toUpperCase();
            String estadoBDStr = cbEstadoBD.getSelectedItem().toString().toUpperCase();
            String usuarioBD = txtUsuarioBD.getText().trim();
            String password = txtContraseñaBD.getText().trim();

            if (nombreBD.isEmpty() || usuarioBD.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor completa todos los campos obligatorios.");
                return;
            }

            if (!nombreBD.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null,
                        "El nombre de la base de datos solo debe contener letras y números.\n"
                        + "No se permiten espacios, guiones bajos (_) ni caracteres especiales.");
                return;
            }

            TipoBD tipo = TipoBD.valueOf(tipoBDStr);
            EstadoBD estado = EstadoBD.valueOf(estadoBDStr);

            DefaultTableModel modelo = (DefaultTableModel) tablaBDs.getModel();

            // ❌ Verifica si ya existe una BD del mismo tipo
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object tipoExistenteObj = modelo.getValueAt(i, 1);
                if (tipoExistenteObj != null && tipoExistenteObj.toString().equalsIgnoreCase(tipoBDStr)) {
                    JOptionPane.showMessageDialog(null,
                            "Ya se ha agregado una base de datos del tipo " + tipoBDStr + ". Solo se permite una por tipo.");
                    return;
                }
            }

            // ✔ Verifica si ya existe una BD con el mismo nombre y tipo
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object nombreObj = modelo.getValueAt(i, 0);
                Object tipoObj = modelo.getValueAt(i, 1);
                if (nombreObj == null || tipoObj == null) {
                    continue;
                }

                String nombreExistente = nombreObj.toString().trim();
                String tipoExistente = tipoObj.toString().trim().toUpperCase();

                if (nombreExistente.equalsIgnoreCase(nombreBD) && tipoExistente.equalsIgnoreCase(tipoBDStr)) {
                    JOptionPane.showMessageDialog(null, "Ya existe una base de datos con ese nombre y tipo.");
                    return;
                }
            }

            String url = obtenerURLBD(cbTipoBD, txtNombreBD);

            BaseDatosFranquicia bdFranquicia = new BaseDatosFranquicia(
                    nombreBD, tipo, estado, url, usuarioBD, password);

            modelo.addRow(new Object[]{
                bdFranquicia.getNombreBD(),
                bdFranquicia.getTipo(),
                bdFranquicia.getEstado(),
                bdFranquicia.getUrlConexion(),
                bdFranquicia.getUsuarioBD()
            });

            if (ListadeBases == null) {
                ListadeBases = new ArrayList<>();
            }

            ListadeBases.add(bdFranquicia);
            this.bases = bdFranquicia;

            JOptionPane.showMessageDialog(null, "Base de datos agregada correctamente.");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Tipo o estado de base de datos inválido.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar base de datos: " + ex.getMessage());
        }
    }

    private void crearTablaUsuariosPorDefecto(int idBD, Usuario creador) throws Exception {

        BaseDatosRepositoryImpl baseDatosRepositoryImpl = new BaseDatosRepositoryImpl();
        BaseDatosFranquicia bd = baseDatosRepositoryImpl.buscarPorId(idBD);

        // Crear metadatos para la tabla "usuarios"
        ObjetoBDFranquicia tablaUsuarios = new ObjetoBDFranquicia();
        tablaUsuarios.setNombreTabla("usuarios");
        tablaUsuarios.setTipoObjeto("TABLA");
        tablaUsuarios.setEsTablaUsuarios(true);
        tablaUsuarios.setIdBD(idBD);
        tablaUsuarios.setFechaCreacion(new Date());
        tablaUsuarios.setColumnas("""
    [
      {"nombre":"id_usuario", "tipo":"entero", "restricciones":"PRIMARY KEY"},
      {"nombre":"nombre_usuario", "tipo":"cadena", "restricciones":"NOT NULL"},
      {"nombre":"password_hash", "tipo":"cadena", "restricciones":"NOT NULL"},
      {"nombre":"es_admin", "tipo":"entero", "restricciones":"DEFAULT 1"}
    ]
    """);

        // Guardar metadatos en las repos
        ObjetoBDRepositoryImpl objetoBDRepository = new ObjetoBDRepositoryImpl();
        objetoBDRepository.guardarObjetoBD(tablaUsuarios);
        objetoBDRepository.guardarObjetoEnMongo(tablaUsuarios);

        // Crear la tabla en la base de datos
        GestorObjetosFactory gestorFactory = new GestorObjetosFactory();
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        gestor.crearObjeto(bd, tablaUsuarios);

        // Determinar tipo de base de datos
        String tipoBD = bd.getTipo().toString().toUpperCase();

        // Construcción dinámica de SQL
        String insertSQL;
        boolean usaSecuenciaOracle = false;

        if (tipoBD.equals("ORACLE")) {
            insertSQL = """
            INSERT INTO usuarios (id_usuario, nombre_usuario, password_hash, es_admin)
            VALUES (?, ?, ?, ?)
        """;
            usaSecuenciaOracle = true;
        } else {
            insertSQL = """
            INSERT INTO usuarios (id_usuario, nombre_usuario, password_hash, es_admin)
            VALUES (?, ?, ?, ?)
        """;
        }

        // Obtener conexión
        try (Connection conn = tipoBD.equals("POSTGRESQL")
                ? ConexionMultifactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                : ConexionMultifactory.getConexion(bd)) {

            PreparedStatement stmt = conn.prepareStatement(insertSQL);

            stmt.setInt(1, creador.getId());
            stmt.setString(2, creador.getCorreo());
            stmt.setString(3, creador.getPasswordHash());
            stmt.setInt(4, 1); // es admin
            stmt.executeUpdate();
        }
    }

    private int preguntarEnQueBDCrearUsuarios(int idFranquicia) {

        BaseDatosRepositoryImpl baseDatosRepositoryImpl = new BaseDatosRepositoryImpl();

        List<BaseDatosFranquicia> bds = baseDatosRepositoryImpl.buscarPorFranquicia(idFranquicia);

        if (bds.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay bases de datos disponibles para esta franquicia.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;  // O algún valor que indique error
        }

        // Construir un arreglo con nombres legibles para mostrar
        String[] opciones = new String[bds.size()];
        for (int i = 0; i < bds.size(); i++) {
            opciones[i] = bds.get(i).getNombreBD() + " (" + bds.get(i).getTipo() + ")";
        }

        String mensaje = "Antes de continuar debes crear la tabla obligatoria 'usuarios'.\n"
                + "Selecciona en qué base de datos deseas crearla:";

        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                mensaje,
                "Seleccionar base de datos",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) {
            // Usuario canceló o cerró el diálogo
            return -1;
        }

        // Buscar el índice seleccionado para devolver el id de la BD
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(seleccion)) {
                return bds.get(i).getId();
            }
        }

        return -1; // No se encontró la selección
    }

    public void registrarBases(List<BaseDatosFranquicia> bases, int idFranquicia) throws Exception {
        BaseDatosRepositoryImpl baseDatosRepositoryImpl = new BaseDatosRepositoryImpl();
        for (BaseDatosFranquicia bd : bases) {
            bd.setId(idFranquicia);
            baseDatosRepositoryImpl.guardar(bd, idFranquicia);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelOculto;
    private javax.swing.JScrollPane Scroloculto;
    private javax.swing.JTable TablaOculta;
    private javax.swing.JButton btnAñadirBD;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JToggleButton btnVer;
    private javax.swing.JComboBox<String> cbEstadoBD;
    private javax.swing.JComboBox<String> cbTipoBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaBDs;
    private javax.swing.JPasswordField txtContraseñaBD;
    private javax.swing.JTextField txtNombreBD;
    private javax.swing.JTextField txtUsuarioBD;
    // End of variables declaration//GEN-END:variables
}
