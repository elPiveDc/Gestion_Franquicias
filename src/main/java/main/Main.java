package main;

import carga.CargaOracleProveedores;

public class Main {
    public static void main(String[] args) {
        CargaOracleProveedores.cargarProveedores("src/main/resources/proveedores.csv");
        CargaOracleProveedores.cargarInsumos("src/main/resources/insumos.csv");
        CargaOracleProveedores.cargarOrdenesCompra("src/main/resources/ordenes_compra.csv");
        CargaOracleProveedores.cargarDetalleOrdenCompra("src/main/resources/detalle_orden_compra.csv");
        CargaOracleProveedores.cargarInspeccionesCalidad("src/main/resources/inspecciones_calidad.csv");
    }
}
