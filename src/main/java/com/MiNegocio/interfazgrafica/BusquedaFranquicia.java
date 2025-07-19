package com.MiNegocio.interfazgrafica;

import com.MiNegocio.accesousuarios.RepositorioAutenticacion;
import com.MiNegocio.configuracioncentral.utils.ValidacionTablaUsuariosService;
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
        setImagenRedimensionada(logo, "/bienvenido.png", 90, 70);
        setIconoRedimensionado(btnBuscarFranquicia, "/lupa.png", 16, 16, 10);
        setIconoRedimensionado(btnCrearFran, "/suma.png", 16, 16, 10);
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        logo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 450));

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 26)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Busque su franquicia:");

        Franquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N

        btnBuscarFranquicia.setBackground(new java.awt.Color(206, 230, 253));
        btnBuscarFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        btnBuscarFranquicia.setText("Buscar");
        btnBuscarFranquicia.setFocusPainted(false);
        btnBuscarFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFranquiciaActionPerformed(evt);
            }
        });

        btnCrearFran.setBackground(new java.awt.Color(206, 230, 253));
        btnCrearFran.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        btnCrearFran.setText("Crear");
        btnCrearFran.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrearFran.setFocusPainted(false);
        btnCrearFran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(32, 66, 118));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 1, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, 45));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 310, 10));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bienvenido.png"))); // NOI18N
        jPanel2.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 120, 80));

        jLabel3.setFont(new java.awt.Font("Swis721 Lt BT", 0, 20)); // NOI18N
        jLabel3.setText("<html><div style='text-align: center;'>En caso de no tener una franquicia<br>comience por crear una</div></html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(Franquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearFran, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(Franquicia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnCrearFran))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
