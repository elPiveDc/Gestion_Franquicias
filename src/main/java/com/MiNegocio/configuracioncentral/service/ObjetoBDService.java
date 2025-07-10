package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;

public interface ObjetoBDService {
    void crearObjeto(int idBD, ObjetoBDFranquicia objeto) throws Exception;
}
