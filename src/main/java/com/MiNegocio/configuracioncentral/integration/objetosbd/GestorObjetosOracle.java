package com.MiNegocio.configuracioncentral.integration.objetosbd;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.factory.ConexionMultifactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;

import java.sql.Connection;
import java.sql.Statement;

public class GestorObjetosOracle implements GestorObjetosBD {

    @Override
    public void crearObjeto(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto) throws Exception {
        
        String sql = SQLHelper.generarCreateSQL(objeto, "ORACLE", bd.getNombreBD());

        try (Connection conn = ConexionMultifactory.getConexion(bd);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabla creada exitosamente en Oracle: " + objeto.getNombreTabla());

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el objeto en Oracle", e);
        }
    }
}
