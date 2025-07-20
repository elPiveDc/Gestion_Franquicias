package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.repository.impl.BaseDatosRepositoryImpl;
import com.MiNegocio.configuracioncentral.repository.impl.ObjetoBDRepositoryImpl;
import com.MiNegocio.configuracioncentral.service.ServicioPdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mongodb.client.gridfs.model.GridFSFile;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import org.bson.types.ObjectId;

public class PanelReportesMain extends JPanel {

    private final ObjetoBDRepositoryImpl objetoRepo;
    private final BaseDatosRepositoryImpl bdRepo;
    private final int idfran;
    private final String bdnombre;
    private final String correoUsuario;

    private final JButton btnReporteCompleto;
    private final JButton btnReporteEspecifico;
    private final JButton btnVerPdfs;
    private final JComboBox<String> comboBD;
    private final JComboBox<String> comboTablas;
    private final Map<String, Integer> mapaNombreIdBD;

    public PanelReportesMain(ObjetoBDRepositoryImpl objetoRepo,
            BaseDatosRepositoryImpl bdRepo,
            int idfran,
            String bdnombre,
            String correoUsuario) {

        this.objetoRepo = objetoRepo;
        this.bdRepo = bdRepo;
        this.idfran = idfran;
        this.bdnombre = bdnombre;
        this.correoUsuario = correoUsuario;

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Generación de Reportes"));

        btnReporteCompleto = new JButton("Generar Reporte Completo");
        btnReporteEspecifico = new JButton("Generar Reporte Específico");
        btnVerPdfs = new JButton("Ver PDFs ya Genereados");
        comboBD = new JComboBox<>();
        comboTablas = new JComboBox<>();
        mapaNombreIdBD = new HashMap<>();

        comboBD.setVisible(false);
        comboTablas.setVisible(false);

        configurarUI();
        cargarBasesDeDatos();
        setupListeners();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnReporteCompleto, gbc);
        gbc.gridy = 1;
        add(btnReporteEspecifico, gbc);
        gbc.gridy = 2;
        add(comboBD, gbc);
        gbc.gridy = 3;
        add(comboTablas, gbc);
        gbc.gridy = 4;
        add(btnVerPdfs, gbc);
    }

    private void configurarUI() {
        java.awt.Font btnFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
        btnReporteCompleto.setFont(btnFont);
        btnReporteEspecifico.setFont(btnFont);
        comboBD.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        comboTablas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    }

    private void cargarBasesDeDatos() {
        try {
            List<BaseDatosFranquicia> lista = bdRepo.obtenerPorFranquicia(idfran);
            comboBD.removeAllItems();
            mapaNombreIdBD.clear();
            for (BaseDatosFranquicia bd : lista) {
                comboBD.addItem(bd.getNombreBD());
                mapaNombreIdBD.put(bd.getNombreBD(), bd.getId());
            }
            if (!lista.isEmpty()) {
                comboBD.setSelectedIndex(0);
                cargarTablasPorBD();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando bases de datos: " + ex.getMessage());
        }
    }

    private void cargarTablasPorBD() {
        comboTablas.removeAllItems();
        String nom = (String) comboBD.getSelectedItem();
        if (nom == null) {
            return;
        }
        int idBD = mapaNombreIdBD.get(nom);
        List<ObjetoBDFranquicia> tablas = objetoRepo.listarObjetosPorBD(idBD);
        comboTablas.addItem("Todas las tablas");
        for (ObjetoBDFranquicia obj : tablas) {
            comboTablas.addItem(obj.getNombreTabla());
        }
    }

    private void setupListeners() {
        btnReporteCompleto.addActionListener(e -> generarReporte(true));
        btnReporteEspecifico.addActionListener(e -> {
            comboBD.setVisible(true);
            comboTablas.setVisible(true);
        });
        comboBD.addActionListener(e -> cargarTablasPorBD());
        comboTablas.addActionListener(e -> {
            if (comboBD.isVisible() && comboTablas.isVisible()) {
                generarReporte(false);
            }
        });
        btnVerPdfs.addActionListener(e -> mostrarYDescargarPdfsGenerados());
    }

    private void mostrarYDescargarPdfsGenerados() {
        ServicioPdf servicioPdf = new ServicioPdf(idfran);
        try {
            List<GridFSFile> archivos = servicioPdf.listarPdfsPorFranquicia();
            if (archivos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron PDFs guardados.");
                return;
            }

            String[] nombres = archivos.stream()
                    .map(GridFSFile::getFilename)
                    .toArray(String[]::new);

            String seleccionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona un PDF para descargar:",
                    "PDFs guardados",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nombres,
                    nombres[0]);

            if (seleccionado != null) {
                GridFSFile archivo = archivos.stream()
                        .filter(f -> f.getFilename().equals(seleccionado))
                        .findFirst()
                        .orElse(null);

                if (archivo != null) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setSelectedFile(new File(seleccionado));
                    fileChooser.setDialogTitle("Guardar PDF desde MongoDB");
                    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                        File destino = fileChooser.getSelectedFile();
                        servicioPdf.descargarPdf((ObjectId) archivo.getObjectId(), destino);
                        JOptionPane.showMessageDialog(this, "PDF descargado exitosamente:\n" + destino.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al recuperar PDFs:\n" + e.getMessage());
        } finally {
            servicioPdf.cerrar();
        }
    }

    private void generarReporte(boolean completo) {
        ServicioPdf servicioPdf = new ServicioPdf(idfran);
        ImagenCouchbaseUtil imagenUtil = new ImagenCouchbaseUtil();
        try {
            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(completo ? "reporte_completo.pdf" : "reporte_especifico.pdf"));
            fileChooser.setDialogTitle("Guardar Reporte");
            if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File destino = fileChooser.getSelectedFile();
            if (!destino.getName().toLowerCase().endsWith(".pdf")) {
                destino = new File(destino.getAbsolutePath() + ".pdf");
            }

            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(destino));
            writer.setPageEvent(new PiePagina());

            doc.open();

            // Insertar la imagen de Couchbase (si existe)
            Image imagen = imagenUtil.obtenerImagenPorIdFranquicia(idfran);
            if (imagen != null) {
                imagen.scaleToFit(100, 100); // tamaño máximo 100x100 px
                imagen.setAlignment(Element.ALIGN_LEFT);
                doc.add(imagen);
                doc.add(Chunk.NEWLINE);
            }

            // Franquicia alineada izquierda
            Paragraph franquicia = new Paragraph(bdnombre, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
            franquicia.setAlignment(Element.ALIGN_LEFT);
            doc.add(franquicia);

            doc.add(new Paragraph("Usuario (correo): " + correoUsuario, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            doc.add(new Paragraph("Fecha y hora: " + fecha, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            doc.add(Chunk.NEWLINE);

            List<BaseDatosFranquicia> bases = bdRepo.obtenerPorFranquicia(idfran);
            for (BaseDatosFranquicia bd : bases) {
                if (!completo && !bd.getNombreBD().equals(comboBD.getSelectedItem().toString())) {
                    continue;
                }

                Paragraph baseTitulo = new Paragraph("Base de Datos: " + bd.getNombreBD(),
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
                baseTitulo.setSpacingBefore(15f);
                baseTitulo.setSpacingAfter(5f);
                doc.add(baseTitulo);
                // línea separadora arriba del contenido
                doc.add(new LineSeparator(1f, 100f, BaseColor.GRAY, 1, 0));

                List<ObjetoBDFranquicia> tablas = objetoRepo.listarObjetosPorBD(bd.getId());
                for (ObjetoBDFranquicia tabla : tablas) {
                    if (!completo && !"Todas las tablas".equals(comboTablas.getSelectedItem().toString())
                            && !tabla.getNombreTabla().equals(comboTablas.getSelectedItem())) {
                        continue;
                    }

                    Paragraph tablaTitulo = new Paragraph(tabla.getNombreTabla(),
                            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13));
                    tablaTitulo.setSpacingBefore(10f);
                    tablaTitulo.setSpacingAfter(3f);
                    doc.add(tablaTitulo);
                    // línea antes de la tabla
                    doc.add(new LineSeparator(0.5f, 100f, BaseColor.LIGHT_GRAY, 1, 0));

                    PdfPTable tablaPdf = generarTablaContenido(bd, tabla.getNombreTabla());
                    doc.add(tablaPdf);
                    doc.add(Chunk.NEWLINE);
                }
            }

            // espacio para firma
            doc.add(Chunk.NEWLINE);
            doc.add(new Paragraph("Nombre: __________________________________________"));
            doc.add(Chunk.NEWLINE);
            doc.add(new Paragraph("DNI:    __________________________________________"));
            doc.add(Chunk.NEWLINE);
            doc.add(new Paragraph("Firma:  __________________________________________"));

            doc.close();

            servicioPdf.guardarPdf(destino, destino.getName());

            JOptionPane.showMessageDialog(this, "Reporte guardado en:\n" + destino.getAbsolutePath());

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generando PDF:\n" + ex.getMessage());
        } finally {
            imagenUtil.cerrar();
        }
    }

    private PdfPTable generarTablaContenido(BaseDatosFranquicia bd, String nombreTabla) {
        String sql;
        String tipoBD = bd.getTipo().toString();

        if ("POSTGRESQL".equalsIgnoreCase(tipoBD)) {
            sql = "SELECT * FROM " + nombreTabla + " LIMIT 50";
        } else if ("ORACLE".equalsIgnoreCase(tipoBD)) {
            sql = "SELECT * FROM " + nombreTabla + " WHERE ROWNUM <= 50";
        } else if ("MYSQL".equalsIgnoreCase(tipoBD)) {
            sql = "SELECT * FROM " + nombreTabla + " LIMIT 50";
        } else if ("SQLSERVER".equalsIgnoreCase(tipoBD)) {
            sql = "SELECT TOP 50 * FROM " + nombreTabla;
        } else {
            // Default: sin límite (puede ser riesgoso con muchas filas)
            sql = "SELECT * FROM " + nombreTabla;
        }

        try (Connection conn = "POSTGRESQL".equalsIgnoreCase(tipoBD)
                ? ConexionMultifactory.getConexion("POSTGRESQL", bd.getUrlConexion())
                : ConexionMultifactory.getConexion(bd); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            PdfPTable table = new PdfPTable(cols);
            table.setWidthPercentage(100);

            for (int i = 1; i <= cols; i++) {
                PdfPCell header = new PdfPCell(new Phrase(meta.getColumnLabel(i),
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(header);
            }

            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    String valor = rs.getString(i);
                    table.addCell(valor != null ? valor : "");
                }
            }
            return table;

        } catch (Exception ex) {
            PdfPTable fallback = new PdfPTable(1);
            PdfPCell cell = new PdfPCell(new Phrase("Error: " + ex.getMessage()));
            fallback.addCell(cell);
            return fallback;
        }
    }

    static class PiePagina extends PdfPageEventHelper {

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, BaseColor.GRAY);

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("Página " + writer.getPageNumber(), font);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() + document.left()) / 2,
                    document.bottom() - 10, 0);
        }
    }
}
