//UWU

package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.repository.impl.UsuarioRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.impl.UsuarioServiceImpl;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class PanelCrearUsuario extends javax.swing.JPanel {

    private VentanaPrincipalCreacion ventanaPrincipalCreacion;
    private UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioRepositoryImpl());

    public PanelCrearUsuario(VentanaPrincipalCreacion ventanaPrincipalCreacion) {
        this.ventanaPrincipalCreacion = ventanaPrincipalCreacion;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnSiguiente = new javax.swing.JButton();
        btnCrearUsuario = new javax.swing.JButton();

        setBackground(new java.awt.Color(253, 253, 253));

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 36)); // NOI18N
        jLabel1.setText("Creación de Usuario");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        jLabel9.setText("Nombre del Usuario:");

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        jLabel2.setText("Correo:");

        jTextField1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        jLabel3.setText("Contraseña:");

        jPasswordField1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        jCheckBox1.setBackground(new java.awt.Color(253, 253, 253));
        jCheckBox1.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        jCheckBox1.setText("Ver contraseña");
        jCheckBox1.setFocusPainted(false);

        btnSiguiente.setBackground(new java.awt.Color(253, 253, 253));
        btnSiguiente.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        btnSiguiente.setText("<html><div style='text-align: center;'>Ir a la creación<br>de la franquicia</div></html> ");
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnCrearUsuario.setBackground(new java.awt.Color(253, 253, 253));
        btnCrearUsuario.setFont(new java.awt.Font("Swis721 Lt BT", 0, 18)); // NOI18N
        btnCrearUsuario.setText("Crear Usuario");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBox1)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(btnCrearUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(106, 106, 106))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(135, 135, 135)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jCheckBox1))
                    .addComponent(btnCrearUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (!ventanaPrincipalCreacion.isUsuarioCreado()) {
            JOptionPane.showMessageDialog(this, "Debes crear un usuario primero.");
            return;
        }

        ventanaPrincipalCreacion.mostrarPanel("crearFranquicia");
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        String nombre = jTextField1.getText().trim();
        String correo = jTextField2.getText().trim();
        String password = new String(jPasswordField1.getPassword()).trim();

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, correo, password, LocalDateTime.now());

        try {
            usuarioService.registrarUsuario(nuevoUsuario);

            // ✅ IMPORTANTE: guardar el usuario en la ventana principal
            ventanaPrincipalCreacion.setUsuarioActual(nuevoUsuario);
            ventanaPrincipalCreacion.setUsuarioCreado(true);

            JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + e.getMessage());
        }

    }//GEN-LAST:event_btnCrearUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
