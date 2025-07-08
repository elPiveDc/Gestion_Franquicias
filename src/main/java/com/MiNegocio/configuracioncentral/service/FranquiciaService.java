package com.MiNegocio.configuracioncentral.service;

import com.MiNegocio.configuracioncentral.domain.Franquicia;
import java.util.List;

public interface FranquiciaService {
    void registrarFranquicia(int idUsuario, Franquicia franquicia);
    List<Franquicia> listarFranquiciasPorUsuario(int idUsuario);
    Franquicia obtenerFranquiciaPorId(int id);
}
