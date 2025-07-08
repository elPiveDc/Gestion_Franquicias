package com.MiNegocio.configuracioncentral.repository;

import com.MiNegocio.configuracioncentral.domain.BaseDatosFranquicia;
import java.util.List;

public interface BaseDatosRepository {
    void guardar(BaseDatosFranquicia bd);
    List<BaseDatosFranquicia> obtenerPorFranquicia(int idFranquicia);
    BaseDatosFranquicia buscarPorId(int id);
}

