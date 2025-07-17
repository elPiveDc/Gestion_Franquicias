package com.MiNegocio.interfazgrafica;

import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;

public class VentanaPrincipalCreacion extends javax.swing.JFrame {

    CardLayout cardLayout;

    String NombreFran;

    public String getNombreFran() {
        return NombreFran;
    }

    public void setNombreFran(String NombreFran) {
        this.NombreFran = NombreFran;
    }

    public VentanaPrincipalCreacion() {
        initComponents();
        setLocationRelativeTo(null);

        decorarBoton(btnMain);
        decorarBoton(btnCrearFranquicia);
        decorarBoton(btnConfBD);
        decorarBoton(btnObjetos);
        decorarBoton(btnCerrarSesion);
        setIconoBoton(btnMain, "/main.png", 30, 30);
        setIconoBoton(btnCrearFranquicia, "/crear.png", 30, 30);
        setIconoBoton(btnConfBD, "/conf.png", 30, 30);
        setIconoBoton(btnObjetos, "/tabla-removebg-preview.png", 30, 30);
        setIconoBoton(btnCerrarSesion, "/cerrar.png", 30, 30);


        cardLayout = (CardLayout) panelContenedor.getLayout();

        // Carga los paneles externos para caragada y subida de datos
        panelContenedor.add(new CrearUsuario(), "Usuario");
        panelContenedor.add(new PanelCrearFranquicia(), "crearFranquicia");
        panelContenedor.add(new PanelConfBD(), "confBD");
        

        cardLayout.show(panelContenedor, "crearFranquicia");
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
        btnCerrarSesion = new javax.swing.JButton();
        btnCrearFranquicia = new javax.swing.JButton();
        btnConfBD = new javax.swing.JButton();
        btnObjetos = new javax.swing.JButton();
        btnMain = new javax.swing.JButton();
        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(32, 66, 118));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 550));

        btnCerrarSesion.setBackground(new java.awt.Color(32, 66, 118));
        btnCerrarSesion.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrar.png"))); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCerrarSesion.setOpaque(true);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnCrearFranquicia.setBackground(new java.awt.Color(32, 66, 118));
        btnCrearFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnCrearFranquicia.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearFranquicia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crear.png"))); // NOI18N
        btnCrearFranquicia.setText("<html>Crear<br>Franquicias</html> ");
        btnCrearFranquicia.setBorderPainted(false);
        btnCrearFranquicia.setFocusPainted(false);
        btnCrearFranquicia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearFranquicia.setOpaque(true);
        btnCrearFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranquiciaActionPerformed(evt);
            }
        });

        btnConfBD.setBackground(new java.awt.Color(32, 66, 118));
        btnConfBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnConfBD.setForeground(new java.awt.Color(255, 255, 255));
        btnConfBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conf.png"))); // NOI18N
        btnConfBD.setText("<html>Configurar bases<br>de datos</html> ");
        btnConfBD.setBorderPainted(false);
        btnConfBD.setFocusPainted(false);
        btnConfBD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnConfBD.setOpaque(true);
        btnConfBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfBDActionPerformed(evt);
            }
        });

        btnObjetos.setBackground(new java.awt.Color(32, 66, 118));
        btnObjetos.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnObjetos.setForeground(new java.awt.Color(255, 255, 255));
        btnObjetos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tabla-removebg-preview.png"))); // NOI18N
        btnObjetos.setText("Objetos");
        btnObjetos.setBorderPainted(false);
        btnObjetos.setFocusPainted(false);
        btnObjetos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnObjetos.setOpaque(true);
        btnObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObjetosActionPerformed(evt);
            }
        });

        btnMain.setBackground(new java.awt.Color(32, 66, 118));
        btnMain.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnMain.setForeground(new java.awt.Color(255, 255, 255));
        btnMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main.png"))); // NOI18N
        btnMain.setText("Principal");
        btnMain.setBorderPainted(false);
        btnMain.setFocusPainted(false);
        btnMain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMain.setOpaque(true);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnObjetos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnConfBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnConfBD, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        panelContenedor.setBackground(new java.awt.Color(253, 253, 253));
        panelContenedor.setPreferredSize(new java.awt.Dimension(800, 500));
        panelContenedor.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipalCreacion().setVisible(true);
        });
    }
    
    private void decorarBoton(javax.swing.JButton boton) {
        java.awt.Color colorNormal = new java.awt.Color(32, 66, 118);
        java.awt.Color colorHover = new java.awt.Color(25, 55, 100);
        java.awt.Color colorPressed = new java.awt.Color(25, 55, 100);


        boton.setBackground(colorNormal);
        boton.setForeground(java.awt.Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorNormal);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorPressed);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (boton.getBounds().contains(evt.getPoint())) {
                    boton.setBackground(colorHover);
                } else {
                    boton.setBackground(colorNormal);
                }
            }
        });
    }

    private void setIconoBoton(javax.swing.JButton boton, String rutaImagen, int ancho, int alto) {
        try {
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));

            // Opcional: icono a la izquierda y texto a la derecha
            boton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            boton.setIconTextGap(14); // espacio entre icono y texto

        } catch (NullPointerException e) {
            System.err.println("No se encontró la imagen: " + rutaImagen);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConfBD;
    private javax.swing.JButton btnCrearFranquicia;
    private javax.swing.JButton btnMain;
    private javax.swing.JButton btnObjetos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
