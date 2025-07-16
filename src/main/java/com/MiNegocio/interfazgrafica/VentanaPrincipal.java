package com.MiNegocio.interfazgrafica;

import java.awt.CardLayout;

public class VentanaPrincipal extends javax.swing.JFrame {

    CardLayout cardLayout;
    
    String NombreFran;

    public String getNombreFran() {
        return NombreFran;
    }

    public void setNombreFran(String NombreFran) {
        this.NombreFran = NombreFran;
    }

    public VentanaPrincipal() {
        initComponents();

        cardLayout = (CardLayout) panelContenedor.getLayout();

        // Carga los paneles externos
        panelContenedor.add(new PanelSesion(this), "sesion");
        panelContenedor.add(new PanelMain(), "main");
        panelContenedor.add(new PanelCrearFranquicia(), "crearFranquicia");
        panelContenedor.add(new PanelConfBD(), "confBD");
        panelContenedor.add(new PanelObjetos(), "objetos");

        // Mostrar por defecto el panel principal
        cardLayout.show(panelContenedor, "sesion");
        jPanel1.setVisible(false);
        
    }

    public void iniciarSesionExitosa() {
        jPanel1.setVisible(true);
        cardLayout.show(panelContenedor, "main");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSesion = new javax.swing.JButton();
        btnFranquicia = new javax.swing.JButton();
        btnCrearFranquicia = new javax.swing.JButton();
        btnConfBD = new javax.swing.JButton();
        btnObjetos = new javax.swing.JButton();
        btnMain = new javax.swing.JButton();
        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnSesion.setText("Sesion");
        btnSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesionActionPerformed(evt);
            }
        });

        btnFranquicia.setText("Franquicia");
        btnFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFranquiciaActionPerformed(evt);
            }
        });

        btnCrearFranquicia.setText("Crear Franquicia");
        btnCrearFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranquiciaActionPerformed(evt);
            }
        });

        btnConfBD.setText("ConfBD");
        btnConfBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfBDActionPerformed(evt);
            }
        });

        btnObjetos.setText("Objetos");
        btnObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObjetosActionPerformed(evt);
            }
        });

        btnMain.setText("Principal");
        btnMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFranquicia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnObjetos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfBD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        panelContenedor.setPreferredSize(new java.awt.Dimension(800, 500));
        panelContenedor.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionActionPerformed
        cardLayout.show(panelContenedor, "sesion");
    }//GEN-LAST:event_btnSesionActionPerformed

    private void btnFranquiciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFranquiciaActionPerformed
        cardLayout.show(panelContenedor, "franquicia");
    }//GEN-LAST:event_btnFranquiciaActionPerformed

    private void btnCrearFranquiciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFranquiciaActionPerformed
        cardLayout.show(panelContenedor, "crearFranquicia");
    }//GEN-LAST:event_btnCrearFranquiciaActionPerformed

    private void btnConfBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfBDActionPerformed
        cardLayout.show(panelContenedor, "confBD");
    }//GEN-LAST:event_btnConfBDActionPerformed

    private void btnObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObjetosActionPerformed
        cardLayout.show(panelContenedor, "objetos");
    }//GEN-LAST:event_btnObjetosActionPerformed

    private void btnMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainActionPerformed
        cardLayout.show(panelContenedor, "main");
    }//GEN-LAST:event_btnMainActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfBD;
    private javax.swing.JButton btnCrearFranquicia;
    private javax.swing.JButton btnFranquicia;
    private javax.swing.JButton btnMain;
    private javax.swing.JButton btnObjetos;
    private javax.swing.JButton btnSesion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
