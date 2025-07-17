package com.MiNegocio.interfazgrafica;

import com.MiNegocio.accesousuarios.RepositorioAutenticacion;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BusquedaFranquicia extends javax.swing.JFrame {

    public BusquedaFranquicia() {
        initComponents();
        ImageIcon iconoLupa = new ImageIcon(getClass().getResource("/lupa.png"));

        Image imagenEscalada = iconoLupa.getImage().getScaledInstance(
                16,
                16,
                Image.SCALE_SMOOTH
        );
        btnBuscarFranquicia.setIcon(new ImageIcon(imagenEscalada));
        btnBuscarFranquicia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscarFranquicia.setIconTextGap(10);
        setLocationRelativeTo(null);
        btnBuscarFranquicia.requestFocusInWindow();
        agregarPlaceholder();
    }

    //Buscador de Frnaquicia en BD Principal
    RepositorioAutenticacion repo = new RepositorioAutenticacion();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Franquicia = new javax.swing.JTextField();
        btnBuscarFranquicia = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Busque su franquicia:");

        Franquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 22)); // NOI18N

        btnBuscarFranquicia.setBackground(new java.awt.Color(206, 230, 253));
        btnBuscarFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 22)); // NOI18N
        btnBuscarFranquicia.setText("Buscar");
        btnBuscarFranquicia.setFocusPainted(false);
        btnBuscarFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFranquiciaActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(206, 230, 253));
        jButton2.setFont(new java.awt.Font("Swis721 Lt BT", 0, 22)); // NOI18N
        jButton2.setText("Crear un Nueva Franquicia");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(32, 66, 118));

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 1, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 157, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(155, 155, 155))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(Franquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnBuscarFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Franquicia)
                    .addComponent(btnBuscarFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(49, 49, 49))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VentanaPrincipalCreacion v1 = new VentanaPrincipalCreacion();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Franquicia;
    private javax.swing.JButton btnBuscarFranquicia;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
