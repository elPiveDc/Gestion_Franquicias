package com.MiNegocio.configuracioncentral.factory;

import com.MiNegocio.configuracioncentral.domain.TipoBD;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosBD;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosMySQL;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosOracle;
import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosPostgreSQL;
// import com.MiNegocio.configuracioncentral.integration.objetosbd.GestorObjetosOracle;

public class GestorObjetosFactory {

    public GestorObjetosBD obtenerGestor(TipoBD tipoBD) {
        return switch (tipoBD) {
            case MYSQL ->
                new GestorObjetosMySQL();
            case POSTGRESQL ->
                new GestorObjetosPostgreSQL();
            case ORACLE ->
                new GestorObjetosOracle();
            default ->
                throw new IllegalArgumentException("Tipo de BD no soportado: " + tipoBD);
        };
    }
}
