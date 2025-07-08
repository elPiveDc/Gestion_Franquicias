package com.MiNegocio.configuracioncentral.integration.basedatos;

public class GestorCreacionBDFactory {

    public static GestorCreacionBD getGestor(String tipoBD) {
        switch (tipoBD.toUpperCase()) {
            case "MYSQL":
                return new GestorCreacionMySQL();
            case "ORACLE":
                return new GestorCreacionOracle();
            case "POSTGRESQL":
                return new GestorCreacionPostgreSQL();
            case"MONGODB":
                return null;

            //Añadir mas
            default:
                throw new UnsupportedOperationException("Tipo de BD no soportado: " + tipoBD);
        }
    }
}
