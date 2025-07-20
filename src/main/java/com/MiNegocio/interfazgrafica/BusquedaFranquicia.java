package com.MiNegocio.interfazgrafica;

import com.MiNegocio.accesousuarios.RepositorioAutenticacion;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BusquedaFranquicia extends javax.swing.JFrame {

    public BusquedaFranquicia() {
        initComponents();
        setLocationRelativeTo(null);
        btnBuscarFranquicia.requestFocusInWindow();
        agregarPlaceholder();
        setImagenRedimensionada(fondo, "/FondoMiNegocio.png", 460, 550);
        setIconoRedimensionado(btnBuscarFranquicia, "/lupa.png", 20, 20, 0);
        setIconoRedimensionado(btnCrearFran, "/suma.png", 20, 20, 0);
        setTitle("Mi Negocio");
        setIconImage(new ImageIcon(getClass().getResource("/logochiquito.png")).getImage());

    }

    RepositorioAutenticacion repo = new RepositorioAutenticacion();

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Franquicia = new javax.swing.JTextField();
        btnBuscarFranquicia = new javax.swing.JButton();
        btnCrearFran = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        fondo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 450));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 26)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("BUSCAR FRANQUICIA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 290, -1));

        Franquicia.setFont(new java.awt.Font("Franklin Gothic Book", 0, 20)); // NOI18N
        jPanel1.add(Franquicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 350, 40));

        btnBuscarFranquicia.setBackground(new java.awt.Color(94, 195, 241));
        btnBuscarFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        btnBuscarFranquicia.setFocusPainted(false);
        btnBuscarFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFranquiciaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarFranquicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 50, 40));

        btnCrearFran.setBackground(new java.awt.Color(94, 195, 241));
        btnCrearFran.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        btnCrearFran.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrearFran.setFocusPainted(false);
        btnCrearFran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrearFran, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 50, 40));

        jPanel2.setBackground(new java.awt.Color(32, 66, 118));
        jPanel2.setMaximumSize(new java.awt.Dimension(460, 550));
        jPanel2.setMinimumSize(new java.awt.Dimension(460, 550));
        jPanel2.setPreferredSize(new java.awt.Dimension(460, 550));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 550));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, 553));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 26)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("<html>¿NO TIENE UNA FRANQUICIA?<br>¡CREE UNA!</html>");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html>SISTEMA GESTOR DE<br>FRANQUICIAS</html>");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnBuscarFranquiciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFranquiciaActionPerformed
        String nombreFranquicia = Franquicia.getText().trim();

        if (nombreFranquicia.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingresa el nombre de la franquicia.",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (repo.existeFranquicia(nombreFranquicia)) {
            VentanaPrincipalAdmin v1 = new VentanaPrincipalAdmin();
            v1.setLocationRelativeTo(null);
            v1.setVisible(true);
            v1.setNombreFran(nombreFranquicia);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "La franquicia \"" + nombreFranquicia + "\" no existe en el sistema.",
                    "Franquicia no encontrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarFranquiciaActionPerformed

    private void btnCrearFranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFranActionPerformed
        VentanaPrincipalCreacion v1 = new VentanaPrincipalCreacion();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearFranActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BusquedaFranquicia().setVisible(true);
        });
    }

    private void agregarPlaceholder() {
        String placeholder = "Nombre de la franquicia";
        Franquicia.setText(placeholder);
        Franquicia.setForeground(java.awt.Color.GRAY);

        Franquicia.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (Franquicia.getText().equals(placeholder)) {
                    Franquicia.setText("");
                    Franquicia.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (Franquicia.getText().isEmpty()) {
                    Franquicia.setForeground(java.awt.Color.GRAY);
                    Franquicia.setText(placeholder);
                }
            }
        });
    }

    public void setImagenRedimensionada(JLabel label, String rutaImagen, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenEscalada));
    }

    public void setIconoRedimensionado(JButton boton, String rutaImagen, int ancho, int alto, int gapTexto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagen = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagen));
        boton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        boton.setIconTextGap(gapTexto);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Franquicia;
    private javax.swing.JButton btnBuscarFranquicia;
    private javax.swing.JButton btnCrearFran;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
