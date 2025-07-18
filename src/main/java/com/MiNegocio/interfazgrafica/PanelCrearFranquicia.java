//UWU
package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.EstadoFranquicia;
import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.repository.impl.FranquiciaRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.UsuarioRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.ServicioSubidaImagenes;
import com.MiNegocio.configuracioncentral.service.impl.FranquiciaServiceImpl;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class PanelCrearFranquicia extends javax.swing.JPanel {

    private VentanaPrincipalCreacion ventanaPrincipalCreacion;
    private PanelConfBD PanelConfBD;

    public PanelConfBD getPanelConfBD() {
        return PanelConfBD;
    }

    public void setPanelConfBD(PanelConfBD PanelConfBD) {
        this.PanelConfBD = PanelConfBD;
    }

    public PanelCrearFranquicia(VentanaPrincipalCreacion ventanaPrincipalCreacion) {
        this.ventanaPrincipalCreacion = ventanaPrincipalCreacion;
        initComponents();

        Usuario usuario = ventanaPrincipalCreacion.getUsuarioActual();
        if (usuario != null) {
            txtCorreoAdmin.setText(usuario.getCorreo());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreFranquicia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCorreoAdmin = new javax.swing.JTextField();
        btnCrearFranquicia = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 30)); // NOI18N
        jLabel1.setText("Crear Nueva Franquicia");

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        jLabel2.setText("Nombre de la Franquicia:");

        txtNombreFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        jLabel4.setText("Administrador (Correo):");

        txtCorreoAdmin.setEditable(false);
        txtCorreoAdmin.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        btnCrearFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        btnCrearFranquicia.setText("Crear Franquicia");
        btnCrearFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranquiciaActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(236, 236, 236))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreoAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addComponent(btnLimpiar)
                .addGap(31, 31, 31)
                .addComponent(btnCrearFranquicia)
                .addContainerGap(203, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearFranquiciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFranquiciaActionPerformed

        String nombreFranq = txtNombreFranquicia.getText().trim();

        if (nombreFranq.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un nombre para la franquicia.");
            return;
        }

        Usuario usuario = ventanaPrincipalCreacion.getUsuarioActual();

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el usuario. Vuelve a crear uno.");
            return;
        }

        Franquicia nuevaFranquicia = new Franquicia(nombreFranq, LocalDateTime.now(), EstadoFranquicia.ACTIVA);
        nuevaFranquicia.setPropietario(usuario);

        try {
            FranquiciaServiceImpl franquiciaService = new FranquiciaServiceImpl(
                    new FranquiciaRepositoryImpl(),
                    new UsuarioRepositoryImpl()
            );

            franquiciaService.registrarFranquicia(usuario.getId(), nuevaFranquicia);
            ServicioSubidaImagenes servicio = new ServicioSubidaImagenes();
            servicio.iniciar(nuevaFranquicia.getId()); // Ya no se pide por pantalla

            ventanaPrincipalCreacion.setFranquiciaActual(nuevaFranquicia);
            JOptionPane.showMessageDialog(this, "Franquicia creada exitosamente.");

            ventanaPrincipalCreacion.setFranquiciaActual(nuevaFranquicia);
            ventanaPrincipalCreacion.setFranquiciaCreada(true);
            ventanaPrincipalCreacion.setFranquiciaActual(nuevaFranquicia);

            // Cambiar al siguiente panel
            ventanaPrincipalCreacion.mostrarPanel("confBD", nuevaFranquicia.getId(), nuevaFranquicia.getNombre());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear la franquicia: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCrearFranquiciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearFranquicia;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCorreoAdmin;
    private javax.swing.JTextField txtNombreFranquicia;
    // End of variables declaration//GEN-END:variables
}
