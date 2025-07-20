package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import java.util.List;

public interface BaseDatosService {
    void registrarBaseDatos(int idFranquicia, BaseDatosFranquicia bd);
    List<BaseDatosFranquicia> listarBasesDatosPorFranquicia(int idFranquicia);
    BaseDatosFranquicia obtenerBaseDatosPorId(int id);
}
