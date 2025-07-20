package com.MiNegocio.configuracioncentral.utils;

import com.MiNegocio.configuracioncentral.factory.ConexionCouch;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.Collection;
import com.itextpdf.text.Image;

import java.util.Base64;

public class ImagenCouchbaseUtil {

    private ConexionCouch conexion;

    public ImagenCouchbaseUtil() {
        conexion = new ConexionCouch();
    }

    /**
     * Descarga la imagen desde Couchbase usando idfran, y devuelve la imagen iText.
     * Retorna null si no existe o falla.
     */
    public Image obtenerImagenPorIdFranquicia(int idfran) {
        try {
            Collection collection = conexion.getCollection();
            String docId = "imagen_" + idfran;
            JsonObject json = collection.get(docId).contentAsObject();

            if (json.containsKey("imagenBase64")) {
                String base64Img = json.getString("imagenBase64");
                byte[] imageBytes = Base64.getDecoder().decode(base64Img);

                // Crea la imagen para iText desde bytes
                return Image.getInstance(imageBytes);
            }
        } catch (Exception e) {
            System.err.println("Error obteniendo imagen de Couchbase: " + e.getMessage());
        }
        return null;
    }

    public void cerrar() {
        conexion.cerrar();
    }
}
