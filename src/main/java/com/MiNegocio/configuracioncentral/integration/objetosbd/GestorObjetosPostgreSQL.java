package com.MiNegocio.configuracioncentral.integration.objetosbd;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionBDFactory;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;

import java.sql.Connection;
import java.sql.Statement;

public class GestorObjetosPostgreSQL implements GestorObjetosBD {

    @Override
    public void crearObjeto(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto) throws Exception {

        String sql = SQLHelper.generarCreateSQL(objeto, bd.getTipo().toString(), bd.getNombreBD());

        try (Connection conn = ConexionMultiBDFactory.getConexion(bd.getTipo().toString(), bd.getUrlConexion()); 
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla creada exitosamente en PostgreSQL: " + objeto.getNombreTabla());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
