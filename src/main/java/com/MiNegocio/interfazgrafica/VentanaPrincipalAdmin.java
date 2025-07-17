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
        setIconoBoton(btnReportes, "/cerrar.png", 30, 30);

        cardLayout = (CardLayout) panelContenedor.getLayout();

        // Carga los paneles externos para creacion usuario franquicia y bd
        panelContenedor.add(new PanelSesion(this), "sesion");
        panelContenedor.add(new PanelMain(), "main");

        /*
        panelContenedor.add(new Panel(), "SubirDatosYconsultas");
        panelContenedor.add(new PanelConfBD(), "ConfiguracionBD");
         */
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
        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(32, 66, 118));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 550));

        btnReportes.setBackground(new java.awt.Color(32, 66, 118));
        btnReportes.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrar.png"))); // NOI18N
        btnReportes.setText("Reportes");
        btnReportes.setBorderPainted(false);
        btnReportes.setFocusPainted(false);
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportes.setOpaque(true);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnCrearTablas.setBackground(new java.awt.Color(32, 66, 118));
        btnCrearTablas.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnCrearTablas.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTablas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crear.png"))); // NOI18N
        btnCrearTablas.setText(" Crear Tablas");
        btnCrearTablas.setBorderPainted(false);
        btnCrearTablas.setFocusPainted(false);
        btnCrearTablas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearTablas.setOpaque(true);
        btnCrearTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablasActionPerformed(evt);
            }
        });

        btnConsultas.setBackground(new java.awt.Color(32, 66, 118));
        btnConsultas.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conf.png"))); // NOI18N
        btnConsultas.setText("<html>Carga de Datos<br>y <br>Consultas</html> ");
        btnConsultas.setBorderPainted(false);
        btnConsultas.setFocusPainted(false);
        btnConsultas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnConsultas.setOpaque(true);
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });

        btnMasBD.setBackground(new java.awt.Color(32, 66, 118));
        btnMasBD.setFont(new java.awt.Font("Swis721 Lt BT", 0, 16)); // NOI18N
        btnMasBD.setForeground(new java.awt.Color(255, 255, 255));
        btnMasBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tabla-removebg-preview.png"))); // NOI18N
        btnMasBD.setText("Objetos");
        btnMasBD.setBorderPainted(false);
        btnMasBD.setFocusPainted(false);
        btnMasBD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMasBD.setOpaque(true);
        btnMasBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasBDActionPerformed(evt);
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
                    .addComponent(btnReportes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnMasBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnConsultas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCrearTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCrearTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnMasBD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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
        cardLayout.show(panelContenedor, "objetos");
    }//GEN-LAST:event_btnMasBDActionPerformed

    private void btnMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainActionPerformed

        cardLayout.show(panelContenedor, "main");
    }//GEN-LAST:event_btnMainActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        panelContenedor.add(new PanelReportesMain(objetoRepo, bdRepo, bv.getId_franquicia(), NombreFran, correo), "Reporte");
        cardLayout.show(panelContenedor, "Reporte");
    }//GEN-LAST:event_btnReportesActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipalAdmin().setVisible(true);
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
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnCrearTablas;
    private javax.swing.JButton btnMain;
    private javax.swing.JButton btnMasBD;
    private javax.swing.JButton btnReportes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
