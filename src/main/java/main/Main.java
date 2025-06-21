package main;

import carga.CargaOracleProveedores;
import carga.CargaPostgreSQLProveedores;

public class Main {
    public static void main(String[] args) {
        
        CargaPostgreSQLProveedores.cargarCSV("src/main/resources/proveedores.csv");
    
    }
}
