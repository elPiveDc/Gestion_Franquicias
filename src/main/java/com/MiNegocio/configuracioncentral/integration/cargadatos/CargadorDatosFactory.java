package com.MiNegocio.configuracioncentral.integration.cargadatos;

public class CargadorDatosFactory {

    public static CargadorDatos obtenerCargador(String tipoCarga) {
        return switch (tipoCarga.toLowerCase()) {
            case "csv" ->
                new CargadorDatosCSV();
            case "json" ->
                new CargadorDatosJSON();
            case "manual" ->
                new CargadorDatosManual();
            default ->
                throw new IllegalArgumentException("Tipo de carga no soportado: " + tipoCarga);
        };
    }
}
