package com.MiNegocio.interfazgrafica;

import com.MiNegocio.accesousuarios.RepositorioAutenticacion;
import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.repository.impl.BaseDatosRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.ObjetoBDRepositoryImpl;
import com.MiNegocio.configuracioncentral.utils.PanelCrearTabla;
import com.MiNegocio.configuracioncentral.utils.PanelReportesMain;
import com.MiNegocio.configuracioncentral.utils.PanelSelectorCargaYConsulta;
import java.awt.CardLayout;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPrincipalAdmin extends javax.swing.JFrame {

    CardLayout cardLayout;
    private ObjetoBDFranquicia tablaCreada;

    public ObjetoBDFranquicia getTablaCreada() {
        return tablaCreada;
    }

    public void setTablaCreada(ObjetoBDFranquicia tablaCreada) {
        this.tablaCreada = tablaCreada;
    }

    String NombreFran;

    public String getNombreFran() {
        return NombreFran;
    }

    public void setNombreFran(String NombreFran) {
        this.NombreFran = NombreFran;
    }

    public VentanaPrincipalAdmin() {
        initComponents();
        setLocationRelativeTo(null);

        decorarBoton(btnMain);
        decorarBoton(btnCrearTablas);
        decorarBoton(btnConsultas);
        decorarBoton(btnMasBD);
        decorarBoton(btnReportes);
        setIconoBoton(btnMain, "/main.png", 30, 30);
        setIconoBoton(btnCrearTablas, "/crear.png", 30, 30);
        setIconoBoton(btnConsultas, "/conf.png", 30, 30);
        setIconoBoton(btnMasBD, "/tabla-removebg-preview.png", 30, 30);
        setIconoBoton(btnReportes, "/reporte.png", 30, 30);
        setTitle("Mi Negocio");
        setIconImage(new ImageIcon(getClass().getResource("/logochiquito.png")).getImage());
        setImagenRedimensionada(lblLogo, "/logo.png", 200, 112);

        cardLayout = (CardLayout) panelContenedor.getLayout();

        panelContenedor.add(new PanelSesion(this), "sesion");
        panelContenedor.add(new PanelMain(), "main");

        cardLayout.show(panelContenedor, "sesion");
        jPanel1.setVisible(false);
    }
    String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void iniciarSesionExitosa(String correo) {
        this.correo = correo;
        jPanel1.setVisible(true);
        cardLayout.show(panelContenedor, "main");
        try {
            bv = repo.obtenerBDUsuariosPorFranquicia(NombreFran);
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnReportes = new javax.swing.JButton();
        btnCrearTablas = new javax.swing.JButton();
        btnConsultas = new javax.swing.JButton();
        btnMasBD = new javax.swing.JButton();
        btnMain = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(32, 66, 118));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 550));

        btnReportes.setBackground(new java.awt.Color(32, 66, 118));
        btnReportes.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("Reportes");
        btnReportes.setBorderPainted(false);
        btnReportes.setFocusPainted(false);
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportes.setMaximumSize(new java.awt.Dimension(89, 30));
        btnReportes.setMinimumSize(new java.awt.Dimension(89, 30));
        btnReportes.setOpaque(true);
        btnReportes.setPreferredSize(new java.awt.Dimension(89, 30));
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnCrearTablas.setBackground(new java.awt.Color(32, 66, 118));
        btnCrearTablas.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnCrearTablas.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTablas.setText(" Crear Tablas");
        btnCrearTablas.setBorderPainted(false);
        btnCrearTablas.setFocusPainted(false);
        btnCrearTablas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearTablas.setMaximumSize(new java.awt.Dimension(89, 30));
        btnCrearTablas.setMinimumSize(new java.awt.Dimension(89, 30));
        btnCrearTablas.setOpaque(true);
        btnCrearTablas.setPreferredSize(new java.awt.Dimension(89, 30));
        btnCrearTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablasActionPerformed(evt);
            }
        });

        btnConsultas.setBackground(new java.awt.Color(32, 66, 118));
        btnConsultas.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultas.setText("<html>Carga de Datos<br>y Consultas</html> ");
        btnConsultas.setBorderPainted(false);
        btnConsultas.setFocusPainted(false);
        btnConsultas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnConsultas.setMaximumSize(new java.awt.Dimension(89, 30));
        btnConsultas.setMinimumSize(new java.awt.Dimension(89, 30));
        btnConsultas.setOpaque(true);
        btnConsultas.setPreferredSize(new java.awt.Dimension(89, 30));
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });

        btnMasBD.setBackground(new java.awt.Color(32, 66, 118));
        btnMasBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnMasBD.setForeground(new java.awt.Color(255, 255, 255));
        btnMasBD.setText("Mis BD");
        btnMasBD.setBorderPainted(false);
        btnMasBD.setFocusPainted(false);
        btnMasBD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMasBD.setMaximumSize(new java.awt.Dimension(89, 30));
        btnMasBD.setMinimumSize(new java.awt.Dimension(89, 30));
        btnMasBD.setOpaque(true);
        btnMasBD.setPreferredSize(new java.awt.Dimension(89, 30));
        btnMasBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasBDActionPerformed(evt);
            }
        });

        btnMain.setBackground(new java.awt.Color(32, 66, 118));
        btnMain.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnMain.setForeground(new java.awt.Color(255, 255, 255));
        btnMain.setText("Principal");
        btnMain.setBorderPainted(false);
        btnMain.setFocusPainted(false);
        btnMain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMain.setMaximumSize(new java.awt.Dimension(89, 30));
        btnMain.setMinimumSize(new java.awt.Dimension(89, 30));
        btnMain.setOpaque(true);
        btnMain.setPreferredSize(new java.awt.Dimension(89, 30));
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
                    .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnMasBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnConsultas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCrearTablas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMasBD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        panelContenedor.setBackground(new java.awt.Color(253, 253, 253));
        panelContenedor.setPreferredSize(new java.awt.Dimension(800, 500));
        panelContenedor.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    BaseDatosFranquicia bv;
    RepositorioAutenticacion repo = new RepositorioAutenticacion();
    BaseDatosRepositoryImpl bdRepo = new BaseDatosRepositoryImpl();
    ObjetoBDRepositoryImpl objetoRepo = new ObjetoBDRepositoryImpl();


    private void btnCrearTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTablasActionPerformed
        panelContenedor.add(new PanelCrearTabla(bv.getId_franquicia(), this), "tabla");
        cardLayout.show(panelContenedor, "tabla");
    }//GEN-LAST:event_btnCrearTablasActionPerformed

    private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultasActionPerformed
        panelContenedor.add(new PanelSelectorCargaYConsulta(objetoRepo, bdRepo, bv.getId_franquicia(), NombreFran), "CargaConsulta");
        cardLayout.show(panelContenedor, "CargaConsulta");
    }//GEN-LAST:event_btnConsultasActionPerformed

    private void btnMasBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasBDActionPerformed
        panelContenedor.add(new PanelConfBD(bdRepo.obtenerPorFranquicia(bv.getId_franquicia()), bv.getId_franquicia(), NombreFran), "objetos");
        cardLayout.show(panelContenedor, "objetos");
    }//GEN-LAST:event_btnMasBDActionPerformed

    private void btnMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainActionPerformed

        cardLayout.show(panelContenedor, "main");
    }//GEN-LAST:event_btnMainActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        panelContenedor.add(new PanelReportesMain(objetoRepo, bdRepo, bv.getId_franquicia(), NombreFran, correo), "Reporte");
        cardLayout.show(panelContenedor, "Reporte");
    }//GEN-LAST:event_btnReportesActionPerformed

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

    public void setImagenRedimensionada(JLabel label, String rutaImagen, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenEscalada));
    }
    
    public void mostrarPanelObjetos() {
        panelContenedor.add(new PanelConfBD(bdRepo.obtenerPorFranquicia(bv.getId_franquicia()), bv.getId_franquicia(), NombreFran), "objetos");
        cardLayout.show(panelContenedor, "objetos");
    }

    public JPanel getPanelContenedor() {
        return panelContenedor;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnCrearTablas;
    private javax.swing.JButton btnMain;
    private javax.swing.JButton btnMasBD;
    private javax.swing.JButton btnReportes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
