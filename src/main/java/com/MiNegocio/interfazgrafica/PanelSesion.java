package com.MiNegocio.interfazgrafica;

import com.MiNegocio.accesousuarios.AutenticadorFranquicia;
import com.MiNegocio.configuracioncentral.utils.PasswordUtils;
import javax.swing.JOptionPane;

public class PanelSesion extends javax.swing.JPanel {

    private VentanaPrincipal ventanaPrincipal;

    public PanelSesion(VentanaPrincipal ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
        initComponents();

    }

    AutenticadorFranquicia auth = new AutenticadorFranquicia();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CorreoE = new javax.swing.JTextField();
        Contrseña = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 48)); // NOI18N
        jLabel1.setText("Inicio de Sesión");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        jLabel2.setText("Correo Electrónico:");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        jLabel3.setText("Contraseña:");

        CorreoE.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        CorreoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoEActionPerformed(evt);
            }
        });

        Contrseña.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N

        btnIniciarSesion.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        btnIniciarSesion.setText("Ingresar");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(184, 184, 184)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Contrseña, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(254, 254, 254))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(CorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(Contrseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String NombreFran = ventanaPrincipal.getNombreFran();
        String Correo = CorreoE.getText().trim();
        char[] passwordChars = Contrseña.getPassword(); // Obtener los caracteres seguros
        String password = new String(passwordChars);
        System.out.println(password);
        System.out.println(PasswordUtils.hashear(password));
        /*
        if (auth.autenticar(this, "Pajaros bien Ricos", "Jimena@Carros.com", "$2a$10$.0kjgSF9EVsY7HMI6vF/seGIDYOf9N9KM6jKeMjtQxEDWbgSuzclO")) {
            ventanaPrincipal.iniciarSesionExitosa();
        }
         */
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void CorreoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CorreoEActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contrseña;
    private javax.swing.JTextField CorreoE;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
