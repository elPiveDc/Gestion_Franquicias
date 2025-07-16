package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.domain.ObjetoBDFranquicia;
import java.util.Scanner;

public interface ObjetoBDService {
    void crearObjeto(int idBD, ObjetoBDFranquicia objeto) throws Exception;
}
