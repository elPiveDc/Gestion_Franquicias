package com.MiNegocio.interfazgrafica;

import com.MiNegocio.configuracioncentral.domain.Franquicia;
import com.MiNegocio.configuracioncentral.domain.Usuario;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaPrincipalCreacion extends javax.swing.JFrame {

    CardLayout cardLayout;

    private boolean franquiciaCreada = false;

    private boolean usuarioCreado = false;
    private Usuario usuarioActual;
    private Franquicia franquiciaActual;

    public boolean isUsuarioCreado() {
        return usuarioCreado;
    }

    public void setUsuarioCreado(boolean creado) {
        this.usuarioCreado = creado;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setFranquiciaActual(Franquicia franquicia) {
        this.franquiciaActual = franquicia;
    }

    public Franquicia getFranquiciaActual() {
        return franquiciaActual;
    }

    public VentanaPrincipalCreacion() {
        initComponents();
        setLocationRelativeTo(null);

        decorarBoton(btnMain);
        decorarBoton(btnCrearUsuario);
        decorarBoton(btnCrearFranquicia);
        decorarBoton(btnObjetos);
        setIconoBoton(btnMain, "/main.png", 30, 30);
        setIconoBoton(btnCrearUsuario, "/usuario.png", 30, 30);
        setIconoBoton(btnCrearFranquicia, "/fran.png", 30, 30);
        setIconoBoton(btnObjetos, "/tabla-removebg-preview.png", 30, 30);
        setTitle("Mi Negocio");
        setIconImage(new ImageIcon(getClass().getResource("/logochiquito.png")).getImage());
        setImagenRedimensionada(lblLogo, "/logo.png", 200, 112);

        cardLayout = (CardLayout) panelContenedor.getLayout();

        // Carga los paneles externos para caragada y subida de datos - Paneles necesarios
        //Proximo panel con menu de creación
        panelContenedor.add(new PanelMain(), "main");
        //Primer panel, creación de Usuario obligatoria antes de pasar al siguiente
        panelContenedor.add(new PanelCrearUsuario(this), "crearUsuario");
        //Configurar las BDS a crear
        panelContenedor.add(new PanelConfBD(this, 0, null), "confBD");

        cardLayout.show(panelContenedor, "main");
        jPanel1.setVisible(true);
    }

    public void iniciarSesionExitosa() {
        jPanel1.setVisible(true);
        cardLayout.show(panelContenedor, "main");
    }
    
    public void cerrar(){
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCrearUsuario = new javax.swing.JButton();
        btnCrearFranquicia = new javax.swing.JButton();
        btnObjetos = new javax.swing.JButton();
        btnMain = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(32, 66, 118));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 550));

        btnCrearUsuario.setBackground(new java.awt.Color(32, 66, 118));
        btnCrearUsuario.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnCrearUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearUsuario.setText("<html>Crear<br>Usuario</html> ");
        btnCrearUsuario.setBorderPainted(false);
        btnCrearUsuario.setFocusPainted(false);
        btnCrearUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearUsuario.setOpaque(true);
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        btnCrearFranquicia.setBackground(new java.awt.Color(32, 66, 118));
        btnCrearFranquicia.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnCrearFranquicia.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearFranquicia.setText("<html>Crear<br>Franquicia</html> ");
        btnCrearFranquicia.setBorderPainted(false);
        btnCrearFranquicia.setFocusPainted(false);
        btnCrearFranquicia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearFranquicia.setOpaque(true);
        btnCrearFranquicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFranquiciaActionPerformed(evt);
            }
        });

        btnObjetos.setBackground(new java.awt.Color(32, 66, 118));
        btnObjetos.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnObjetos.setForeground(new java.awt.Color(255, 255, 255));
        btnObjetos.setText("<html>Configuración y<br>BD</html> ");
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
                    .addComponent(btnObjetos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCrearUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearFranquicia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        panelContenedor.setBackground(new java.awt.Color(253, 253, 253));
        panelContenedor.setPreferredSize(new java.awt.Dimension(800, 500));
        panelContenedor.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        mostrarPanel("crearUsuario",0,null);
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void btnCrearFranquiciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFranquiciaActionPerformed
        mostrarPanel("crearFranquicia",0, null);
    }//GEN-LAST:event_btnCrearFranquiciaActionPerformed

    private void btnObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObjetosActionPerformed
        mostrarPanel("confBD",0, null);
    }//GEN-LAST:event_btnObjetosActionPerformed

    private void btnMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainActionPerformed
        mostrarPanel("main",0, null);
    }//GEN-LAST:event_btnMainActionPerformed

    public void setImagenRedimensionada(JLabel label, String rutaImagen, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenEscalada));
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

    public void mostrarPanel(String nombrePanel, int fran, String nomfran) {
        switch (nombrePanel) {
            case "crearFranquicia":
                if (!usuarioCreado) {
                    JOptionPane.showMessageDialog(this, "Debes crear primero un usuario.");
                    return;
                }
                if (!franquiciaCreada) {
                    panelContenedor.add(new PanelCrearFranquicia(this), "crearFranquicia");
                }
                break;
            case "confBD":
                if (!usuarioCreado || !franquiciaCreada) {
                    JOptionPane.showMessageDialog(this, "Debes crear primero un usuario y una franquicia.");
                    return;
                }
                break;
        }
        if ("confBD".equals(nombrePanel)) {
            panelContenedor.add(new PanelConfBD(this,fran,nomfran ), "confBD");
        }
        cardLayout.show(panelContenedor, nombrePanel);
    }

    public void setFranquiciaCreada(boolean creada) {
        this.franquiciaCreada = creada;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearFranquicia;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnMain;
    private javax.swing.JButton btnObjetos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
