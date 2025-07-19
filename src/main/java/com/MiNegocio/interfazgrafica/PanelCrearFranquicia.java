//UWU
package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.EstadoFranquicia;
import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import com.MiNegocio.configuracioncentral.repository.impl.FranquiciaRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.UsuarioRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.ServicioSubidaImagenes;
import com.MiNegocio.configuracioncentral.service.impl.FranquiciaServiceImpl;
import java.awt.Image;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
        
        setImagenRedimensionada(logito, "/franquicia.png", 300, 270);

        Usuario usuario = ventanaPrincipalCreacion.getUsuarioActual();
        if (usuario != null) {
            txtCorreoAdmin.setText(usuario.getCorreo());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtNombreFranquicia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCorreoAdmin = new javax.swing.JTextField();
        btnCrearFranquicia = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logito = new javax.swing.JLabel();

        setBackground(new java.awt.Color(253, 253, 253));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 22)); // NOI18N
        jLabel2.setText("Nombre de la Franquicia:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 179, -1, -1));

        txtNombreFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        add(txtNombreFranquicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 219, 245, -1));

        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 0, 22)); // NOI18N
        jLabel4.setText("Administrador (Correo):");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 301, -1, -1));

        txtCorreoAdmin.setEditable(false);
        txtCorreoAdmin.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        add(txtCorreoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 341, 245, -1));

        btnCrearFranquicia.setBackground(new java.awt.Color(47, 90, 156));
        btnCrearFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 1, 20)); // NOI18N
        btnCrearFranquicia.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearFranquicia.setText("Crear Franquicia");
        btnCrearFranquicia.setFocusPainted(false);
        btnCrearFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranquiciaActionPerformed(evt);
            }
        });
        add(btnCrearFranquicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 428, 274, 50));

        jPanel1.setBackground(new java.awt.Color(47, 90, 156));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 100));

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 3, 34)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear Nueva Franquicia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(215, 215, 215))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        add(logito, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 300, 270));
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

    public void setImagenRedimensionada(JLabel label, String rutaImagen, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenEscalada));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearFranquicia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logito;
    private javax.swing.JTextField txtCorreoAdmin;
    private javax.swing.JTextField txtNombreFranquicia;
    // End of variables declaration//GEN-END:variables
}
