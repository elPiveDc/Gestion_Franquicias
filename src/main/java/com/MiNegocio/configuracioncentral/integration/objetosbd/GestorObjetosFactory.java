package com.MiNegocio.configuracioncentral.integration.objetosbd;

import com.MiNegocio.configuracioncentral.domain.TipoBD;

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
