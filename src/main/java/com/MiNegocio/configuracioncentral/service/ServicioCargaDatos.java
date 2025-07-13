package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;

public interface ServicioCargaDatos {
    void cargarDesdeCSV(int idObjeto) throws Exception;
    void cargarDesdeJSON(int idObjeto) throws Exception;
    void cargarDesdeManual(int idObjeto) throws Exception;
}
