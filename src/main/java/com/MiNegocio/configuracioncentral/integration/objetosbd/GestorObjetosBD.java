package com.MiNegocio.configuracioncentral.integration.objetosbd;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;

public interface GestorObjetosBD {
    void crearObjeto(BaseDatosFranquicia bd, ObjetoBDFranquicia objeto) throws Exception;
}