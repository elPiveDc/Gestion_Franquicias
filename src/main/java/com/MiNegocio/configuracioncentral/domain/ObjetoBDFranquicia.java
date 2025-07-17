package com.MiNegocio.configuracioncentral.domain;

import java.util.Date;

public class ObjetoBDFranquicia {

    private int idObjeto;
    private int idBD;
    private String nombreTabla;
    private String tipoObjeto;  // TABLA, VISTA, FUNCION
    private boolean esTablaUsuarios;
    private String columnas; // formato JSON
    private Date fechaCreacion;

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdBD() {
        return idBD;
    }

    public void setIdBD(int idBD) {
        this.idBD = idBD;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public boolean isEsTablaUsuarios() {
        return esTablaUsuarios;
    }

    public void setEsTablaUsuarios(boolean esTablaUsuarios) {
        this.esTablaUsuarios = esTablaUsuarios;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "ObjetoBDFranquicia{"
                + "idObjeto=" + idObjeto
                + ", idBD=" + idBD
                + ", nombreTabla='" + nombreTabla + '\''
                + ", tipoObjeto='" + tipoObjeto + '\''
                + ", esTablaUsuarios=" + esTablaUsuarios
                + ", columnas='" + columnas + '\''
                + ", fechaCreacion=" + fechaCreacion
                + '}';
    }

    public String toString2() {
        return getNombreTabla();
    }

}
