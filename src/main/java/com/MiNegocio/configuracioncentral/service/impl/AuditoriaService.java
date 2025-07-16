package com.MiNegocio.configuracioncentral.service.impl;

import com.MiNegocio.configuracioncentral.factory.ConexionNoSQL;
import com.datastax.astra.client.databases.Database;
import com.datastax.astra.client.collections.Collection;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class AuditoriaService {

    public static void registrarCarga(String usuario, String baseDatos, String tabla, String metodoCarga) {
    try {
        Database db = (Database) ConexionNoSQL.getDatabase();
        Collection<Map> auditoriaCol = db.getCollection("auditoria_cargas", Map.class);

        Map<String, Object> doc = new HashMap<>();
        doc.put("usuario", usuario);
        doc.put("base_datos", baseDatos);
        doc.put("tabla", tabla);
        doc.put("metodo_carga", metodoCarga);
        doc.put("fecha", Instant.now().toString());

        auditoriaCol.insertOne(doc);

        System.out.println("Auditoría registrada correctamente en AstraDB.");
    } catch (Exception e) {
        System.err.println("Error al registrar la auditoría: " + e.getMessage());
        e.printStackTrace();
    }
}
}
