package main;

import carga.CargaOracleProveedores;
import carga.CargaPostgreSQLProveedores;

public class Main {
    public static void main(String[] args) {
        CargaOracleProveedores.cargarCSV("src/main/resources/proveedores.csv");
        CargaPostgreSQLProveedores.cargarCSV("src/main/resources/proveedores.csv");
    
    }
}
