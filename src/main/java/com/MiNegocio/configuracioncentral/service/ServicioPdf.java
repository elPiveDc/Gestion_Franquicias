package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.factory.ConexionNoSQL;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServicioPdf {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final GridFSBucket gridFSBucket;
    private final int idFran;

    public ServicioPdf(int idFran) {
        this.mongoClient = ConexionNoSQL.getMongoConexion();
        this.database = mongoClient.getDatabase("bdcentral");
        this.gridFSBucket = GridFSBuckets.create(database, "pdf_reportes");
        this.idFran = idFran;
    }

    /**
     * Sube un archivo PDF a MongoDB con metadata de franquicia.
     */
    public void guardarPdf(File archivoPdf, String nombreArchivo) throws IOException {
        try (FileInputStream fis = new FileInputStream(archivoPdf)) {
            GridFSUploadOptions opciones = new GridFSUploadOptions()
                    .metadata(new Document("tipo", "pdf")
                            .append("id_franquicia", idFran)
                            .append("fecha", new Date())
                            .append("nombreOriginal", nombreArchivo));

            gridFSBucket.uploadFromStream(nombreArchivo, fis, opciones);
        }
    }

    public List<GridFSFile> listarPdfsPorFranquicia() {
        List<GridFSFile> resultados = new ArrayList<>();

        for (GridFSFile file : gridFSBucket.find(Filters.eq("metadata.id_franquicia", idFran))) {
            resultados.add(file);
        }

        return resultados;
    }

    public void descargarPdf(ObjectId idArchivo, File destino) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(destino)) {
            gridFSBucket.downloadToStream(idArchivo, fos);
        }
    }

    public void cerrar() {
        ConexionNoSQL.cerrarConexion();
    }
}
