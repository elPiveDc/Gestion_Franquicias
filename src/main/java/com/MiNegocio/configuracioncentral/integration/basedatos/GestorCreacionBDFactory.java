package com.MiNegocio.configuracioncentral.integration.basedatos;

import javax.swing.JOptionPane;

public class GestorCreacionBDFactory {

    public static GestorCreacionBD getGestor(String tipoBD) {
        switch (tipoBD.toUpperCase()) {
            case "MYSQL":
                return new GestorCreacionMySQL();
            case "ORACLE":
                return new GestorCreacionOracle();
            case "POSTGRESQL":
                return new GestorCreacionPostgreSQL();
            //Añadir mas
            default:
                JOptionPane.showMessageDialog(null,
                    "Tipo de BD no soportado: " + tipoBD,
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
                throw new UnsupportedOperationException("Tipo de BD no soportado: " + tipoBD);
        }
    }
}
