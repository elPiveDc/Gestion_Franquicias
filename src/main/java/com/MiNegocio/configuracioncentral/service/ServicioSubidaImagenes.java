package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.factory.ConexionCouchbaseCloud;
import com.couchbase.client.java.json.JsonObject;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ServicioSubidaImagenes {

    private ConexionCouchbaseCloud conexion;

    public ServicioSubidaImagenes() {
        conexion = new ConexionCouchbaseCloud();
    }

    private String imagenAStringBase64(String rutaArchivo) throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of(rutaArchivo));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public void subirImagen(int clave, String rutaArchivo) throws IOException {
        String base64Imagen = imagenAStringBase64(rutaArchivo);

        JsonObject documento = JsonObject.create()
                .put("clave", clave)
                .put("imagenBase64", base64Imagen);

        String idDocumento = "imagen_" + clave;

        conexion.subirDocumento(idDocumento, documento.toString());

        JOptionPane.showMessageDialog(null, "Imagen subida con id: " + idDocumento,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cerrar() {
        conexion.cerrar();
    }

    /**
     * Método que inicia el proceso de selección de archivo y subida, recibiendo
     * directamente la clave (id).
     */
    public void iniciar(int clave) {
        try {
            // Selector de archivo
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                subirImagen(clave, rutaArchivo);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error leyendo la imagen: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrar();
        }
    }

    // Este main ahora solo sirve para testeo manual
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServicioSubidaImagenes servicio = new ServicioSubidaImagenes();
            servicio.iniciar(123); // Para pruebas, puedes pasar el ID directo
        });
    }
}
