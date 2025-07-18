package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.*;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosFactory;
import com.MiNegocio.configuracioncentral.repository.impl.*;
import com.MiNegocio.configuracioncentral.utils.ValidacionTablaUsuariosService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    }

    public PanelConfBD(List<BaseDatosFranquicia> lista, int idFranquicia, String nomfran) {
        this.vienecreacion = "adios";
        this.nomfran = nomfran;
        this.franquiciaRepository = new FranquiciaRepositoryImpl();
        this.idFranquicia = idFranquicia;
        initComponents();
        llenarTablaDesdeLista(lista, TablaOculta);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 36)); // NOI18N
        jLabel1.setText("Configurar Bases de Datos");

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel2.setText("Nombre BD:");

        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel4.setText("Tipo de BD:");

        jLabel5.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel5.setText("Usuario BD:");

        jLabel6.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        jLabel6.setText("Contraseña BD:");

        btnFinalizar.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        btnFinalizar.setText("Finalizar Creación");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        txtNombreBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N

        cbTipoBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        cbTipoBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MYSQL", "POSTGRESQL", "ORACLE" }));
        cbTipoBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoBDActionPerformed(evt);
            }
        });

        txtUsuarioBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N

        txtContraseñaBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N

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

        btnAñadirBD.setText("Agregar Base");
        btnAñadirBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirBDActionPerformed(evt);
            }
        });

        cbEstadoBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONFIGURADA", "NO_CONFIGURADA", "ERROR" }));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAñadirBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbEstadoBD, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreBD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTipoBD, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                    .addComponent(txtUsuarioBD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContraseñaBD, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Scroloculto, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelOculto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombreBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbTipoBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtUsuarioBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelOculto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtContraseñaBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(cbEstadoBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Scroloculto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnAñadirBD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            registrarBases(ListadeBases, idFranquicia);
            JOptionPane.showMessageDialog(null,
                    "Su(s) Base(s) de Dato Fueron Creada(s)",
                    "CONFIRMACION",
                    JOptionPane.OK_OPTION);
            if ("creacion".equals(vienecreacion)) {
                VentanaPrincipalAdmin v1 = new VentanaPrincipalAdmin();
                v1.setLocationRelativeTo(null);
                v1.setVisible(true);
                v1.setNombreFran(nomfran);
                ventanaPrincipal.cerrar();
            }
        } catch (Exception ex) {
            Logger.getLogger(PanelConfBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnAñadirBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirBDActionPerformed
        agregarBaseDatos(txtNombreBD, cbTipoBD, txtUsuarioBD, txtContraseñaBD, cbEstadoBD, tablaBDs);
    }//GEN-LAST:event_btnAñadirBDActionPerformed

    private void cbTipoBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoBDActionPerformed

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
                url = "jdbc:postgresql://localhost:5432/" + nombreBD;
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

            // Validación de campos obligatorios
            if (nombreBD.isEmpty() || usuarioBD.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor completa todos los campos obligatorios.");
                return;
            }

            // Conversión a enums
            TipoBD tipo = TipoBD.valueOf(tipoBDStr);
            EstadoBD estado = EstadoBD.valueOf(estadoBDStr);

            // Validación de duplicados
            DefaultTableModel modelo = (DefaultTableModel) tablaBDs.getModel();
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

            // Construcción de URL
            String url = obtenerURLBD(cbTipoBD, txtNombreBD);

            // Crear objeto BaseDatosFranquicia
            BaseDatosFranquicia bdFranquicia = new BaseDatosFranquicia(
                    nombreBD,
                    tipo,
                    estado,
                    url,
                    usuarioBD,
                    password // Si usas hash, reemplaza por la versión hasheada aquí
            );

            // Agregar a la tabla
            modelo.addRow(new Object[]{
                bdFranquicia.getNombreBD(),
                bdFranquicia.getTipo(),
                bdFranquicia.getEstado(),
                bdFranquicia.getUrlConexion(),
                bdFranquicia.getUsuarioBD()
            });

            // Asegurar que la lista esté inicializada
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

        GestorObjetosFactory gestorFactory = new GestorObjetosFactory();

        // Crear tabla
        GestorObjetosBD gestor = gestorFactory.obtenerGestor(bd.getTipo());
        gestor.crearObjeto(bd, tablaUsuarios);

        ObjetoBDRepositoryImpl objetoBDRepository = new ObjetoBDRepositoryImpl();

        // Registrar en objetos_bd_franquicia
        objetoBDRepository.guardarObjetoBD(tablaUsuarios);
        objetoBDRepository.guardarObjetoEnMongo(tablaUsuarios);

        // Insertar el usuario creador
        if (bd.getTipo().toString().equals("POSTGRESQL")) {
            try (Connection conn = ConexionMultiBDFactory.getConexion("POSTGRESQL", bd.getUrlConexion())) {
                String insertSQL = """
                INSERT INTO usuarios (id_usuario, nombre_usuario, password_hash, es_admin)
                VALUES (?, ?, ?, ?)
            """;
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setInt(1, creador.getId());
                stmt.setString(2, creador.getCorreo()); // o getNombre() si quieres nombre
                stmt.setString(3, creador.getPasswordHash());
                stmt.setInt(4, 1); // es admin
                stmt.executeUpdate();
            }
        } else {
            try (Connection conn = ConexionMultiBDFactory.getConexion(bd)) {
                String insertSQL = """
                INSERT INTO usuarios (id_usuario, nombre_usuario, password_hash, es_admin)
                VALUES (?, ?, ?, ?)
            """;
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setInt(1, creador.getId());
                stmt.setString(2, creador.getCorreo()); // o getNombre() si quieres nombre
                stmt.setString(3, creador.getPasswordHash());
                stmt.setInt(4, 1); // es admin
                stmt.executeUpdate();
            }
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
        UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();

        for (BaseDatosFranquicia bd : bases) {
            bd.setId(idFranquicia);
            baseDatosRepositoryImpl.guardar(bd, idFranquicia);
        }

        if ("creacion".equals(vienecreacion)) {
            int idBDSeleccionada = preguntarEnQueBDCrearUsuarios(idFranquicia);
            // Obtener el usuario creador
            Usuario creador = usuarioRepository.obtenerCreadorDeFranquicia(idFranquicia);

            // Crear la tabla e insertar al creador
            crearTablaUsuariosPorDefecto(idBDSeleccionada, creador);

            JOptionPane.showMessageDialog(null,
                    "La tabla 'usuarios' fue creada correctamente con el usuario inicial.",
                    "CONFIRMACION",
                    JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Usuario y negocio registrados correctamente.");
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelOculto;
    private javax.swing.JScrollPane Scroloculto;
    private javax.swing.JTable TablaOculta;
    private javax.swing.JButton btnAñadirBD;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JComboBox<String> cbEstadoBD;
    private javax.swing.JComboBox<String> cbTipoBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaBDs;
    private javax.swing.JPasswordField txtContraseñaBD;
    private javax.swing.JTextField txtNombreBD;
    private javax.swing.JTextField txtUsuarioBD;
    // End of variables declaration//GEN-END:variables
}
