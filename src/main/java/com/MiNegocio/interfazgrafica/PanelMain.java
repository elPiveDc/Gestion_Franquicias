package com.MiNegocio.interfazgrafica;

import java.awt.Image;
import javax.swing.ImageIcon;

public class PanelMain extends javax.swing.JPanel {

    public PanelMain() {
        initComponents();
        
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/gestion.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        labelLogo.setIcon(new ImageIcon(imagenEscalada));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(253, 253, 253));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 36)); // NOI18N
        jLabel1.setText("<html><div style='text-align: center;'>Bienvenido al sistema de<br>gestión de franquicias</div></html> ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 410, -1));

        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestion.png"))); // NOI18N
        add(labelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 80, 80));

        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel4.setText("- Crear tablas y gestionar los datos almacenados en esas bases.");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 143, 590, 40));

        jLabel5.setFont(new java.awt.Font("Swis721 Lt BT", 1, 16)); // NOI18N
        jLabel5.setText("Instrucciones principales");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel7.setText("- Crear nuevas franquicias en el sistema.");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        jLabel8.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel8.setText("- Configurar las bases de datos que utiliza cada franquicia.");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        jLabel9.setFont(new java.awt.Font("Swis721 Lt BT", 1, 16)); // NOI18N
        jLabel9.setText("Descripción breve del Sistema:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel10.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel10.setText("En “Objetos” podrás crear tablas dentro de las bases de datos y realizar consultas o cargar datos.");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        jLabel11.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel11.setText("<html>Este software permite administrar franquicias y sus bases de datos de forma centralizada.<br> Desde aquí podrás:</html>");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel12.setFont(new java.awt.Font("Swis721 Lt BT", 1, 14)); // NOI18N
        jLabel12.setText("1.- Crear Franquicias");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jLabel13.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel13.setText("Utiliza el menú “Crear Franquicia” para registrar una nueva franquicia en el sistema principal.");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        jLabel14.setFont(new java.awt.Font("Swis721 Lt BT", 1, 14)); // NOI18N
        jLabel14.setText("2.- Configurar Bases de Datos");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, -1));

        jLabel15.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jLabel15.setText("<html>Accede a “Configurar BD” para crear, editar o eliminar las bases de datos que almacenarán<br>la información de la franquicia.</html>");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        jLabel16.setFont(new java.awt.Font("Swis721 Lt BT", 1, 14)); // NOI18N
        jLabel16.setText("3.- Gestionar Objetos");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelLogo;
    // End of variables declaration//GEN-END:variables
}
