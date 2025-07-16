package com.MiNegocio.configuracioncentral.integration.objetosbd;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import com.MiNegocio.configuracioncentral.factory.ConexionMultiBDFactory;
import com.MiNegocio.configuracioncentral.utils.SQLHelper;

import java.sql.Connection;
import java.sql.Statement;

public class GestorObjetosMySQL implements GestorObjetosBD {

    @Override
    public void crearObjeto(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto) throws Exception {
        
        String sql = SQLHelper.generarCreateSQL(objeto, bd.getTipo().toString(), bd.getNombreBD());

        try (Connection conn = ConexionMultiBDFactory.getConexion(bd);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Objeto creado correctamente en MySQL: " + objeto.getNombreTabla());
            
        } catch (Exception e) {
            throw new RuntimeException("Error al crear objeto en MySQL", e);
        }
    }
}
