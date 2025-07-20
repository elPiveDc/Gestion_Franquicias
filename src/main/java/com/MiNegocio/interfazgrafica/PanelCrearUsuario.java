//UWU
package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.repository.impl.UsuarioRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.impl.UsuarioServiceImpl;
import java.awt.Image;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PanelCrearUsuario extends javax.swing.JPanel {

    private VentanaPrincipalCreacion ventanaPrincipalCreacion;
    private UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioRepositoryImpl());

    public PanelCrearUsuario(VentanaPrincipalCreacion ventanaPrincipalCreacion) {
        this.ventanaPrincipalCreacion = ventanaPrincipalCreacion;
        initComponents();
        setIconoRedimensionadoToggle(btnVerContra, "/ojo.png", 25, 25, 0);
        setImagenRedimensionada(logoCrearUsuario, "/usuario.png", 225, 263);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnCrearUsuario = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVerContra = new javax.swing.JToggleButton();
        logoCrearUsuario = new javax.swing.JLabel();

        setBackground(new java.awt.Color(253, 253, 253));
        setMaximumSize(new java.awt.Dimension(800, 550));
        setMinimumSize(new java.awt.Dimension(800, 550));
        setPreferredSize(new java.awt.Dimension(800, 550));

        jLabel9.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        jLabel9.setText("Nombre del Usuario:");

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        jLabel2.setText("Correo:");

        txtUsuario.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        txtCorreo.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Swis721 Lt BT", 0, 24)); // NOI18N
        jLabel3.setText("Contraseña:");

        txtPassword.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        btnCrearUsuario.setBackground(new java.awt.Color(47, 90, 156));
        btnCrearUsuario.setFont(new java.awt.Font("Swis721 Lt BT", 1, 20)); // NOI18N
        btnCrearUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearUsuario.setText("Crear Usuario");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(47, 90, 156));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 100));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Creación de Usuario");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnVerContra.setBorderPainted(false);
        btnVerContra.setContentAreaFilled(false);
        btnVerContra.setFocusPainted(false);
        btnVerContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerContraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(104, 104, 104)
                        .addComponent(logoCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnVerContra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(logoCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        String nombre = txtUsuario.getText().trim();
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.");
            return;
        }

        if (!esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(this, "El correo ingresado no es válido.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, correo, password, LocalDateTime.now());

        try {
            usuarioService.registrarUsuario(nuevoUsuario);

            ventanaPrincipalCreacion.setUsuarioActual(nuevoUsuario);
            ventanaPrincipalCreacion.setUsuarioCreado(true);

            JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
            if (!ventanaPrincipalCreacion.isUsuarioCreado()) {
                JOptionPane.showMessageDialog(this, "Debes crear un usuario primero.");
                return;
            }
            ventanaPrincipalCreacion.mostrarPanel("crearFranquicia", 0, null);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void btnVerContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerContraActionPerformed
        if (btnVerContra.isSelected()) {
            // Mostrar contraseña
            txtPassword.setEchoChar((char) 0); // Muestra texto sin ocultar
            btnVerContra.setToolTipText("Ocultar contraseña");
        } else {
            // Ocultar contraseña
            txtPassword.setEchoChar('•'); // Vuelve a ocultar
            btnVerContra.setToolTipText("Mostrar contraseña");
        }
    }//GEN-LAST:event_btnVerContraActionPerformed

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
    
    public void setImagenRedimensionada(JLabel label, String rutaImagen, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenEscalada));
    }

    private boolean esCorreoValido(String correo) {
        // Expresión regular básica para validar correos comunes
        String regexCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regexCorreo);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JToggleButton btnVerContra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logoCrearUsuario;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
