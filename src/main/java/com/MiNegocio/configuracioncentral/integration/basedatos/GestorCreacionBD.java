package com.MiNegocio.configuracioncentral.integration.basedatos;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;

public interface GestorCreacionBD {

    void crearBaseDatos(BaseDatosFranquicia bd) throws Exception;
}
